package com.timesete.projeto5.business.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesete.projeto5.business.converter.IUPartConverter;
import com.timesete.projeto5.model.dto.IUPart.IUPartRequest;
import com.timesete.projeto5.model.dto.IUPart.IUPartResponse;
import com.timesete.projeto5.model.entity.IUPartModel;
import com.timesete.projeto5.model.entity.PartModel;
import com.timesete.projeto5.model.repository.IUPartRepository;
import com.timesete.projeto5.model.repository.PartRepository;

@Service
public class IUPartService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private IUPartRepository iuPartRepository;

    public IUPartResponse createIUPart(String partId, IUPartRequest request) throws Exception {

        PartModel partModel = partRepository.findById(partId)
                .orElseThrow(() -> new Exception("Part not found"));

        IUPartModel iuPartModel = IUPartConverter.toModel(request, partModel);

        iuPartRepository.save(iuPartModel);

        return IUPartConverter.toResponse(iuPartModel);
    }

    public IUPartResponse updateIUPart(String iuPartId, IUPartRequest request) throws Exception {

        IUPartModel iuPartModel = iuPartRepository.findById(iuPartId)
                .orElseThrow(() -> new Exception("IUPart not found"));

        iuPartModel.setLastModifiedAt(LocalDateTime.now());
        iuPartModel.setState(request.getState());
        iuPartModel.setTimeInUse(request.getTimeInUse());

        iuPartRepository.updateIUPartById(iuPartId, iuPartModel);

        return IUPartConverter.toResponse(iuPartModel);
    }

    public IUPartResponse findById(String id) throws Exception {
        return IUPartConverter.toResponse(iuPartRepository.findIUPartById(id)
                .orElseThrow(() -> new RuntimeException("IUPart not found")));
    }
}
