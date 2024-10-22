package com.kidsworld.kidsping.domain.kid.service;

import com.kidsworld.kidsping.domain.kid.dto.request.CreateKidRequest;
import com.kidsworld.kidsping.domain.kid.dto.request.UpdateKidRequest;
import com.kidsworld.kidsping.domain.kid.dto.response.CreateKidResponse;
import com.kidsworld.kidsping.domain.kid.dto.response.DeleteKidResponse;
import com.kidsworld.kidsping.domain.kid.dto.response.GetKidResponse;
import com.kidsworld.kidsping.domain.kid.dto.response.UpdateKidResponse;
import com.kidsworld.kidsping.domain.kid.dto.request.KidMBTIDiagnosisRequest;

public interface KidService {

    CreateKidResponse createKid(CreateKidRequest request);
    GetKidResponse getKid(Long kidId);
    UpdateKidResponse updateKid(Long kidId, UpdateKidRequest request);
    DeleteKidResponse deleteKid(Long kidId);
    void diagnoseKidMBTI(KidMBTIDiagnosisRequest diagnosisRequest);


}