package com.jipsa.jipsaserver.contract;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping
    public ResponseEntity<List<ContractResponse>> getAll(@AuthenticationPrincipal String email) {
        return ResponseEntity.ok(contractService.getAll(email));
    }

    @PostMapping
    public ResponseEntity<ContractResponse> create(@AuthenticationPrincipal String email,
                                                   @RequestBody ContractRequest req) {
        return ResponseEntity.ok(contractService.create(email, req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractResponse> update(@AuthenticationPrincipal String email,
                                                   @PathVariable Long id,
                                                   @RequestBody ContractRequest req) {
        return ResponseEntity.ok(contractService.update(email, id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal String email,
                                       @PathVariable Long id) {
        contractService.delete(email, id);
        return ResponseEntity.noContent().build();
    }
}
