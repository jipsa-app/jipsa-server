package com.jipsa.jipsaserver.contract;

import com.jipsa.jipsaserver.domain.Contract;
import com.jipsa.jipsaserver.domain.ContractRepository;
import com.jipsa.jipsaserver.domain.Member;
import com.jipsa.jipsaserver.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ContractService {

    private final ContractRepository contractRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<ContractResponse> getAll(String email) {
        Member member = findMember(email);
        return contractRepository.findByMemberIdOrderByCreatedAtDesc(member.getId())
                .stream().map(ContractResponse::new).collect(Collectors.toList());
    }

    public ContractResponse create(String email, ContractRequest req) {
        Member member = findMember(email);
        Contract contract = Contract.builder()
                .memberId(member.getId())
                .type(req.getType())
                .address(req.getAddress())
                .contractDate(req.getContractDate())
                .balanceDate(req.getBalanceDate())
                .expiryDate(req.getExpiryDate())
                .memo(req.getMemo())
                .build();
        return new ContractResponse(contractRepository.save(contract));
    }

    public ContractResponse update(String email, Long id, ContractRequest req) {
        Contract contract = findContract(email, id);
        contract.update(req.getAddress(), req.getType(), req.getContractDate(),
                req.getBalanceDate(), req.getExpiryDate(), req.getMemo());
        return new ContractResponse(contractRepository.save(contract));
    }

    public void delete(String email, Long id) {
        contractRepository.delete(findContract(email, id));
    }

    private Contract findContract(String email, Long id) {
        Member member = findMember(email);
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다."));
        if (!contract.getMemberId().equals(member.getId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        return contract;
    }

    private Member findMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }
}
