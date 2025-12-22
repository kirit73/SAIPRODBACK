package com.sai.inventory.service.impl;

import com.sai.inventory.domain.common.request.RequestByIdReq;
import com.sai.inventory.domain.login.request.LoginRequest;
import com.sai.inventory.domain.login.response.LoginResponse;
import com.sai.inventory.domain.login.response.UsersMasterResp;
import com.sai.inventory.domain.master.request.GeneralParamReq;
import com.sai.inventory.domain.master.response.CommonIdResponse;
import com.sai.inventory.domain.master.response.GeneralParamRes;
import com.sai.inventory.entity.LocationMaster;
import com.sai.inventory.entity.OrganizationMaster;
import com.sai.inventory.entity.UsersMaster;
import com.sai.inventory.exception.ValidationException;
import com.sai.inventory.repository.UserMasterRepository;
import com.sai.inventory.service.GeneralParamsService;
import com.sai.inventory.service.LoginService;
import com.sai.inventory.service.MasterService;
import com.sai.inventory.util.CommonUtils;
import com.sai.inventory.util.ErrorResponseConstant;
import com.sai.inventory.util.GenParamTypeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private MasterService masterService;

    @Autowired
    private UserMasterRepository userMasterRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GeneralParamsService generalParamsService;

    @Transactional
    @Override
    public Object authenticateUser(LoginRequest req) {
        if (CommonUtils.isBlank(req.getUserCd())) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }

        if (CommonUtils.isBlank(req.getPassword())) {
            throw new ValidationException(ErrorResponseConstant.LOGIN_PWD_INVALID, ErrorResponseConstant.LOGIN_PWD_INVALID_MSG);
        }

        List<UsersMaster> allByUserCd = userMasterRepository.findAllByUserCd(req.getUserCd());
        if (allByUserCd == null || allByUserCd.size() == 0) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }

        UsersMaster usersMaster = allByUserCd.get(0);
       /* String reqPass = passwordEncoder.encode(req.getPassword());
        if (!usersMaster.getPassword().equalsIgnoreCase(reqPass)) {
            throw new ValidationException(ErrorResponseConstant.INVALID_LOGIN, ErrorResponseConstant.INVALID_LOGIN_MSG);
        }*/

        if (!"A".equalsIgnoreCase(usersMaster.getStatus())) {
            throw new ValidationException(ErrorResponseConstant.INACTIVE_USER, ErrorResponseConstant.INACTIVE_USER_MSG);
        }

        CommonIdResponse response2 = new CommonIdResponse();
        response2.setId(usersMaster.getOrganizationId());
        OrganizationMaster orgMasterById = masterService.getOrgMasterById(response2);

        LocationMaster locationMasterById = null;
        if (orgMasterById != null && orgMasterById.getLocationId() != null) {
            RequestByIdReq requestByIdReq = new RequestByIdReq();
            requestByIdReq.setRequestId(orgMasterById.getLocationId());
            requestByIdReq.setUserId(req.getUserCd());
            locationMasterById = masterService.getLocationMasterById(requestByIdReq);
        }

        LoginResponse loginResponse = new LoginResponse();

        UsersMasterResp userMasterResp = new UsersMasterResp();
        BeanUtils.copyProperties(usersMaster, userMasterResp);

        GeneralParamReq id = new GeneralParamReq();
        id.setParamType(GenParamTypeEnum.USER_TYPE.name());
        id.setParamVal(userMasterResp.getUserType());
        List<GeneralParamRes> generalParams = generalParamsService.getGeneralParams(id);
        if (generalParams != null && !generalParams.isEmpty()) {
            userMasterResp.setUserTypeDesc(generalParams.get(0).getParamDesc());
        }
        loginResponse.setUserDetails(userMasterResp);
        loginResponse.setOrganizationDetails(orgMasterById);
        loginResponse.setLocationDetails(locationMasterById);

        usersMaster.setLastLoginDate(new Date());
        userMasterRepository.save(usersMaster);

        return loginResponse;
    }
}
