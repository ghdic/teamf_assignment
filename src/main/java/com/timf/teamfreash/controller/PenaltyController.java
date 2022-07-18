package com.timf.teamfreash.controller;

import com.timf.teamfreash.model.Penalty;
import com.timf.teamfreash.model.dto.PenaltyDto;
import com.timf.teamfreash.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/penalty")
public class PenaltyController {

    private PenaltyService penaltyService;

    @Autowired
    public PenaltyController(PenaltyService penaltyService) {
        this.penaltyService = penaltyService;
    }

    @GetMapping("")
    public ResponseEntity<List<PenaltyDto>> showPenaltyList() {
        List<Penalty> penaltyList = penaltyService.getAllPenalty();
        List<PenaltyDto> penaltyDtoList = penaltyList.stream().map(PenaltyDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(penaltyDtoList, HttpStatus.OK);
    }

    @GetMapping("/{defendant_id}")
    public ResponseEntity<List<PenaltyDto>> showPenaltyListForDefendant(@PathVariable long defendant_id) {
        List<Penalty> penaltyList = penaltyService.getDefendantPenalty(defendant_id);
        List<PenaltyDto> penaltyDtoList = penaltyList.stream().map(PenaltyDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(penaltyDtoList, HttpStatus.OK);
    }

    @PostMapping("/accept/{penalty_id}")
    public ResponseEntity<PenaltyDto> checkedPenalty(@PathVariable long penalty_id) {
        Penalty penalty = penaltyService.setPenaltyChecked(penalty_id, true);
        return new ResponseEntity<>(PenaltyDto.from(penalty), HttpStatus.ACCEPTED);
    }
}
