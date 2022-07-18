package com.timf.teamfreash.controller;

import com.timf.teamfreash.model.VOC;
import com.timf.teamfreash.model.dto.VOCDto;
import com.timf.teamfreash.service.ClientStrategy;
import com.timf.teamfreash.service.VOCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/voc")
public class VOCController {
    private VOCService vocService;
    private ClientStrategy clientStrategy;

    @Autowired
    public VOCController(VOCService vocService, ClientStrategy clientStrategy) {
        this.vocService = vocService;
        this.clientStrategy = clientStrategy;
    }

    @PostMapping("")
    public ResponseEntity<VOCDto> remonstrateByVOC(@RequestBody final VOCDto vocDto) {
        VOC voc = vocService.createVOC(VOC.from(vocDto), vocDto.getReason());
        return new ResponseEntity<>(VOCDto.from(voc, clientStrategy.getClient(voc.getComplainerId(),voc.getComplainerType()),
                clientStrategy.getClient(voc.getDefendantId(), voc.getDefendantType())), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<VOCDto>> showVOCList() {
        List<VOC> vocs = vocService.getAllVOC();
        List<VOCDto> vocDtoList = vocs.stream().map(voc -> VOCDto.from(voc, clientStrategy.getClient(voc.getComplainerId(),voc.getComplainerType()),
                clientStrategy.getClient(voc.getDefendantId(), voc.getDefendantType()))).collect(Collectors.toList());
        return new ResponseEntity<>(vocDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VOCDto> showVOCFromId(@PathVariable final long id) {
        VOC voc = vocService.getVOCFromId(id);
        return new ResponseEntity<>(VOCDto.from(voc, clientStrategy.getClient(voc.getComplainerId(),voc.getComplainerType()),
                clientStrategy.getClient(voc.getDefendantId(), voc.getDefendantType())), HttpStatus.OK);
    }

}
