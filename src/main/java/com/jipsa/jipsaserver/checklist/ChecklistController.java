package com.jipsa.jipsaserver.checklist;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/checklist")
@RequiredArgsConstructor
public class ChecklistController {

    private final ChecklistService checklistService;

    @GetMapping
    public ResponseEntity<List<String>> getChecked(@AuthenticationPrincipal String email) {
        return ResponseEntity.ok(checklistService.getCheckedItems(email));
    }

    @PostMapping("/{itemId}/toggle")
    public ResponseEntity<Map<String, Boolean>> toggle(@AuthenticationPrincipal String email,
                                                       @PathVariable String itemId) {
        boolean checked = checklistService.toggle(email, itemId);
        return ResponseEntity.ok(Map.of("checked", checked));
    }
}
