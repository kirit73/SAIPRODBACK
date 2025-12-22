package com.sai.inventory.service.impl;

import com.sai.inventory.dao.MasterDao;
import com.sai.inventory.domain.OrganizationMstResponse;
import com.sai.inventory.domain.common.request.DeptRequestByIdReq;
import com.sai.inventory.domain.common.request.OrgIdRequest;
import com.sai.inventory.domain.common.request.RequestByIdReq;
import com.sai.inventory.domain.master.request.*;
import com.sai.inventory.domain.master.response.*;
import com.sai.inventory.entity.*;
import com.sai.inventory.entity.internal.OHQDataEntity;
import com.sai.inventory.exception.ValidationException;
import com.sai.inventory.repository.*;
import com.sai.inventory.service.MasterService;
import com.sai.inventory.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private LocationMasterRepository locationMasterRepository;
    @Autowired
    private DeptMasterRepository deptMasterRepository;
    @Autowired
    private UOMMasterRepository uomMasterRepository;
    @Autowired
    private OrganizationMasterRepository organizationMasterRepository;
    @Autowired
    private VendorMasterRepository vendorMasterRepository;
    @Autowired
    private EmpMasterRepository empMasterRepository;
    @Autowired
    private LocatorMasterRepository locatorMasterRepository;
    @Autowired
    private UserMasterRepository userMasterRepository;
    @Autowired
    private GeneralParamRepository generalParamRepository;
    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;

    @Value("${parent.org.id}")
    private Long parentOrgId;
    @Autowired
    private MasterDao masterDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GeneralParamsServiceImpl generalParamsService;

    @Override
    public LocationMasterResp saveLocationMaster(LocationMasterReq locationMasterReq) {
        if (locationMasterReq.getLocationId() != null) {
            locationMasterReq.setLocationId(null);
        }
        validateMasterLocationData(locationMasterReq);
        LocationMaster locationMaster = prepareLocationMaster(locationMasterReq);
        Long id = locationMasterRepository.saveAndFlush(locationMaster).getId();
        return new LocationMasterResp(id);
    }

    @Override
    public List<LocationMaster> getLocationMaster(Long orgId) {
        return locationMasterRepository.findAllByStatusAndOrgId("A", orgId);
    }

    @Override
    public LocationMaster getLocationMasterById(RequestByIdReq id) {
        if (id.getRequestId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<LocationMaster> byId = locationMasterRepository.findById(id.getRequestId());
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public LocationMasterResp updateLocationMaster(LocationMasterReq locationMasterReq) {
        if (locationMasterReq.getLocationId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        validateMasterLocationData(locationMasterReq);
        Optional<LocationMaster> byId = locationMasterRepository.findById(locationMasterReq.getLocationId());
        if (byId.isPresent()) {
            LocationMaster locationMaster = prepareLocationMaster(locationMasterReq);
            locationMaster.setUpdateDate(new Date());
            locationMaster.setUpdateUser(locationMasterReq.getUserId());
            locationMaster.setId(byId.get().getId());
            locationMaster.setCreateDate(byId.get().getCreateDate());
            locationMaster.setCreateUser(byId.get().getCreateUser());
            Long id = locationMasterRepository.saveAndFlush(locationMaster).getId();
            return new LocationMasterResp(id);
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public void deleteLocationMaster(RequestByIdReq locationMasterReq) {
        if (locationMasterReq.getRequestId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<LocationMaster> byId = locationMasterRepository.findById(locationMasterReq.getRequestId());
        if (byId.isPresent()) {
            LocationMaster locationMaster = byId.get();
            locationMaster.setStatus("D");
            locationMaster.setUpdateUser(locationMasterReq.getUserId());
            locationMaster.setUpdateDate(new Date());
            locationMasterRepository.saveAndFlush(locationMaster);
            return;
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    private void validateMasterLocationData(LocationMasterReq locationMasterReq) {
        String endDate = locationMasterReq.getEndDate();
        Date date = CommonUtils.getDate(endDate, ApplicationConstant.DATE_FORMAT);
        if (date == null) {
            throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, ErrorResponseConstant.INVALID_DATE_MSG);
        }
        locationMasterReq.setEndDateInDate(date);
    }

    private LocationMaster prepareLocationMaster(LocationMasterReq req) {
        LocationMaster locationMaster = new LocationMaster();
        locationMaster.setLocationName(req.getLocationName());
        locationMaster.setCity(req.getCity());
        locationMaster.setLocationAddr(req.getLocationAddr());
        locationMaster.setLocationType(req.getLocationType());
        locationMaster.setEndDate(req.getEndDateInDate());
        locationMaster.setEmail(req.getEmail());
        locationMaster.setGstin(req.getGstin());
        locationMaster.setPan(req.getPan());
        locationMaster.setZipcode(req.getPincode());
        locationMaster.setLatitude(req.getLatitude());
        locationMaster.setLongitude(req.getLongitude());
        locationMaster.setStatus("A");
        locationMaster.setCreateDate(new Date());
        locationMaster.setCreateUser(req.getUserId());
        locationMaster.setState(req.getState());
        locationMaster.setContactNo(req.getContactNo());
        locationMaster.setOrgId(req.getOrgId());
        return locationMaster;
    }

    @Override
    public DeptMasterResp saveDeptMaster(DepartmentMasterReq req) {
        if (req.getDepartmentId() != null) {
            req.setDepartmentId(null);
        }
        validateMasterDeptData(req);
        DepartmentMaster departmentMaster = prepareDeptMaster(req);
        Long id = deptMasterRepository.saveAndFlush(departmentMaster).getId();
        return new DeptMasterResp(id);
    }

    private DepartmentMaster prepareDeptMaster(DepartmentMasterReq req) {
        DepartmentMaster departmentMaster = new DepartmentMaster();
        departmentMaster.setDepartmentName(req.getDepartmentName());
        departmentMaster.setLocation(req.getLocation());
        departmentMaster.setStatus("A");
        departmentMaster.setCreateUser(req.getUserId());
        departmentMaster.setCreateDate(new Date());
        return departmentMaster;
    }

    private void validateMasterDeptData(DepartmentMasterReq req) {

    }


    @Override
    public List<DepartmentMaster> getDeptMaster() {
        return deptMasterRepository.findAllByStatus("A");
    }

    @Override
    public DepartmentMaster getDeptMasterById(DeptRequestByIdReq id) {
        if (id.getDepartmentId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<DepartmentMaster> byId = deptMasterRepository.findById(id.getDepartmentId());
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public DeptMasterResp updateDeptMaster(DepartmentMasterReq req) {
        if (req.getDepartmentId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        validateMasterDeptData(req);
        Optional<DepartmentMaster> byId = deptMasterRepository.findById(req.getDepartmentId());
        if (byId.isPresent()) {
            DepartmentMaster deptMaster = prepareDeptMaster(req);
            deptMaster.setUpdateDate(new Date());
            deptMaster.setUpdateUser(req.getUserId());
            deptMaster.setId(byId.get().getId());
            deptMaster.setCreateDate(byId.get().getCreateDate());
            deptMaster.setCreateUser(byId.get().getCreateUser());
            Long id = deptMasterRepository.saveAndFlush(deptMaster).getId();
            return new DeptMasterResp(id);
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public void deleteDeptMaster(DeptRequestByIdReq req) {
        if (req.getDepartmentId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<DepartmentMaster> byId = deptMasterRepository.findById(req.getDepartmentId());
        if (byId.isPresent()) {
            DepartmentMaster departmentMaster = byId.get();
            departmentMaster.setStatus("D");
            departmentMaster.setUpdateUser(req.getUserId());
            departmentMaster.setUpdateDate(new Date());
            deptMasterRepository.saveAndFlush(departmentMaster);
            return;
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public CommonIdResponse saveMaster(UOMMasterReq req) {
        if (req.getUomId() != null) {
            req.setUomId(null);
        }
        validateReqData(req);
        UOMMaster uOMMaster = prepareMaster(req);
        Long id = uomMasterRepository.saveAndFlush(uOMMaster).getId();
        return new CommonIdResponse(id, req.getUserId());
    }

    private UOMMaster prepareMaster(UOMMasterReq req) {
        UOMMaster master = new UOMMaster();
        master.setBaseUom(req.getBaseUom());
        master.setClassName(req.getClassName());
        master.setEndDate(req.getEndDateInDate());
        master.setUomCode(req.getUomCode());
        master.setUomDesc(req.getUomDesc());
        master.setUomName(req.getUomName());
        master.setStatus("A");
        master.setCreateDate(new Date());
        master.setCreateUser(req.getUserId());
        return master;
    }

    private void validateReqData(UOMMasterReq req) {
        String endDate = req.getEndDate();
        Date date = CommonUtils.getDate(endDate, ApplicationConstant.DATE_FORMAT);
        if (date == null) {
            throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, ErrorResponseConstant.INVALID_DATE_MSG);
        }
        req.setEndDateInDate(date);
    }

    @Override
    public List<UOMMaster> getUOMMaster() {
        return uomMasterRepository.findAllByStatus("A");
    }

    @Override
    public UOMMaster getMasterById(CommonIdResponse id) {
        if (id.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<UOMMaster> byId = uomMasterRepository.findById(id.getId());
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public CommonIdResponse updateMaster(UOMMasterReq req) {
        if (req.getUomId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        validateReqData(req);
        Optional<UOMMaster> byId = uomMasterRepository.findById(req.getUomId());
        if (byId.isPresent()) {
            UOMMaster master = prepareMaster(req);
            master.setUpdateDate(new Date());
            master.setUpdateUser(req.getUserId());
            master.setId(byId.get().getId());
            master.setCreateDate(byId.get().getCreateDate());
            master.setCreateUser(byId.get().getCreateUser());
            Long id = uomMasterRepository.saveAndFlush(master).getId();
            return new CommonIdResponse(id, req.getUserId());
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public void deleteMaster(CommonIdResponse req) {
        if (req.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<UOMMaster> byId = uomMasterRepository.findById(req.getId());
        if (byId.isPresent()) {
            UOMMaster master = byId.get();
            master.setStatus("D");
            master.setUpdateUser(req.getUserId());
            master.setUpdateDate(new Date());
            uomMasterRepository.saveAndFlush(master);
            return;
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }


    @Override
    public CommonIdResponse saveMaster(OrganizationMasterReq req) {
        if (req.getOrganizationId() != null) {
            req.setOrganizationId(null);
        }
        validateReqData(req);
        OrganizationMaster master = prepareMaster(req);
        Long id = organizationMasterRepository.saveAndFlush(master).getId();
        return new CommonIdResponse(id, req.getUserId());
    }

    private OrganizationMaster prepareMaster(OrganizationMasterReq req) {
        OrganizationMaster master = new OrganizationMaster();
        master.setEmail(req.getEmail());
        master.setGstin(req.getGstin());
        master.setContactNo(req.getContactNo());
        master.setLocation(req.getLocation());
        if (CommonUtils.getLong(req.getLocation()) != null) {
            master.setLocationId(CommonUtils.getLong(req.getLocation()));
        }
        master.setOrganizationName(req.getOrganizationName());
        master.setLocationAddr(req.getLocationAddress());

        Long pOrgId = CommonUtils.getLong(req.getParentOrganizationId());
        master.setParentOrgId(pOrgId);
        if (pOrgId == null) {
            master.setParentOrgId(parentOrgId);
        }
        master.setStatus("A");
        master.setCreateDate(new Date());
        master.setCreateUser(req.getUserId());
        return master;
    }

    private void validateReqData(OrganizationMasterReq req) {

    }

    @Override
    public List<OrganizationMaster> getParentOrganizationMaster() {
        return organizationMasterRepository.findAllByParentOrgId(parentOrgId);
    }

    @Override
    public Object getOHQ(OHQRequest id) {
        List<OHQDataEntity> ohqData = masterDao.getOHQData(id);
        List<OHQMainResponse> response = prepareOHQResponse(ohqData, false);
        return response;
    }

    private List<OHQMainResponse> prepareOHQResponse(List<OHQDataEntity> ohqData, boolean allOHQ) {
        Map map = new HashMap();
        List<OHQMainResponse> rList = new ArrayList<OHQMainResponse>();
        if (ohqData != null) {
            for (OHQDataEntity entity : ohqData) {
                OHQMainResponse r = new OHQMainResponse();
                r.setItemCode(entity.getItemMasterCd());
                r.setItemId(entity.getItemMasterId());
                r.setUomDesc(entity.getUomName());
                r.setUomId(entity.getUomId());
                r.setItemName(entity.getItemMasterDesc());
                if (!allOHQ) {
                    r.setLocationId(entity.getLocationId());
                    r.setLocationName(entity.getLocationName());
                }


                OHQQtyLocResponse locResponse = new OHQQtyLocResponse();
                locResponse.setQuantity(entity.getQuantity());
                locResponse.setLocatorDesc(entity.getLocatorDesc());
                locResponse.setLocatorId(entity.getLocatorId());
                locResponse.setItemId(entity.getItemMasterId());
                locResponse.setPrice(entity.getPrice());
                locResponse.setSubcategory(entity.getSubCategory());
                locResponse.setSubcategoryDesc(entity.getSubCategoryDesc());

                BigDecimal price = entity.getPrice() == null ? BigDecimal.ZERO : entity.getPrice();
                BigDecimal quantity = entity.getQuantity() == null ? BigDecimal.ZERO : new BigDecimal(entity.getQuantity());
                BigDecimal totalVal = price.multiply(quantity);
                locResponse.setTotalValues(totalVal);

                if (allOHQ) {
                    locResponse.setLocationName(entity.getLocationName());
                    locResponse.setLocationId(entity.getLocationId());
                }

                String itemCd = entity.getItemMasterCd();

                if (!map.containsKey(itemCd)) {
                    List<OHQQtyLocResponse> list = new ArrayList<>();
                    list.add(locResponse);
                    r.setQtyList(list);
                } else {
                    OHQMainResponse rsp = (OHQMainResponse) map.get(itemCd);
                    List<OHQQtyLocResponse> list = rsp.getQtyList();
                    list.add(locResponse);
                    r.setQtyList(list);
                }

                map.put(entity.getItemMasterCd(), r);
            }
        }

        for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry thisEntry = (Map.Entry) it.next();
            rList.add((OHQMainResponse) thisEntry.getValue());
        }

        return rList;
    }

    @Override
    public List<OrganizationMaster> getOrganizationMaster() {
        return organizationMasterRepository.findAllByStatus("A");
    }

    @Override
    public OrganizationMaster getOrgMasterById(CommonIdResponse id) {
        if (id.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<OrganizationMaster> byId = organizationMasterRepository.findById(id.getId());
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public List<OrganizationMaster> getOrgMasterByParentId(CommonIdResponse id) {
        if (id.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        return organizationMasterRepository.findAllByParentOrgId(id.getId());
    }

    @Override
    public CommonIdResponse updateMaster(OrganizationMasterReq req) {
        if (req.getOrganizationId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        validateReqData(req);
        Optional<OrganizationMaster> byId = organizationMasterRepository.findById(req.getOrganizationId());
        if (byId.isPresent()) {
            OrganizationMaster master = prepareMaster(req);
            master.setUpdateDate(new Date());
            master.setUpdateUser(req.getUserId());
            master.setId(byId.get().getId());
            master.setCreateDate(byId.get().getCreateDate());
            master.setCreateUser(byId.get().getCreateUser());
            Long id = organizationMasterRepository.saveAndFlush(master).getId();
            return new CommonIdResponse(id, req.getUserId());
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public void deleteOrgMaster(CommonIdResponse req) {
        if (req.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<OrganizationMaster> byId = organizationMasterRepository.findById(req.getId());
        if (byId.isPresent()) {
            OrganizationMaster master = byId.get();
            master.setStatus("D");
            master.setUpdateUser(req.getUserId());
            master.setUpdateDate(new Date());
            organizationMasterRepository.saveAndFlush(master);
            return;
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }


    @Override
    public CommonIdResponse saveMaster(VendorMasterReq req) {
        if (req.getVendorId() != null) {
            req.setVendorId(null);
        }
        validateReqData(req);
        VendorMaster master = prepareMaster(req);
        Long id = vendorMasterRepository.saveAndFlush(master).getId();
        return new CommonIdResponse(id, req.getUserId());
    }

    private VendorMaster prepareMaster(VendorMasterReq req) {
        VendorMaster master = new VendorMaster();
        BeanUtils.copyProperties(req, master);
       /* master.setEmail(req.getEmail());
        master.setGstin(req.getGstin());
        master.setContactNo(req.getContactNo());
        master.setVendorName(req.getVendorName());
        master.setPan(req.getPan());
        master.setAddress(req.getAddress());
        master.setApproval(req.getApproval());*/
        master.setStatus("A");
        master.setCreateDate(new Date());
        master.setCreateUser(req.getUserId());
        return master;
    }

    private void validateReqData(VendorMasterReq req) {

    }

    @Override
    public List<VendorMaster> getVendorMaster() {
        return vendorMasterRepository.findAllByStatus("A");
    }

    @Override
    public VendorMaster getVendorMasterById(CommonIdResponse id) {
        if (id.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<VendorMaster> byId = vendorMasterRepository.findById(id.getId());
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public CommonIdResponse updateMaster(VendorMasterReq req) {
        if (req.getVendorId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        validateReqData(req);
        // Optional<OrganizationMaster> byId = organizationMasterRepository.findById(req.getVendorId());
        Optional<VendorMaster> byId = vendorMasterRepository.findById(req.getVendorId());
        if (byId.isPresent()) {
            VendorMaster master = prepareMaster(req);
            master.setUpdateDate(new Date());
            master.setUpdateUser(req.getUserId());
            master.setId(byId.get().getId());
            master.setCreateDate(byId.get().getCreateDate());
            master.setCreateUser(byId.get().getCreateUser());
            Long id = vendorMasterRepository.saveAndFlush(master).getId();
            return new CommonIdResponse(id, req.getUserId());
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public void deleteVendorMaster(CommonIdResponse req) {
        if (req.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<VendorMaster> byId = vendorMasterRepository.findById(req.getId());
        if (byId.isPresent()) {
            VendorMaster master = byId.get();
            master.setStatus("D");
            master.setUpdateUser(req.getUserId());
            master.setUpdateDate(new Date());
            vendorMasterRepository.saveAndFlush(master);
            return;
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public CommonIdResponse saveMaster(EmployeeMasterReq req) {
        if (req.getEmployeeMasterId() != null) {
            req.setEmployeeMasterId(null);
        }
        validateReqData(req);
        EmployeeMaster master = prepareMaster(req);
        Long id = empMasterRepository.saveAndFlush(master).getId();
        return new CommonIdResponse(id, req.getUserId());
    }

    private EmployeeMaster prepareMaster(EmployeeMasterReq req) {
        EmployeeMaster master = new EmployeeMaster();
        BeanUtils.copyProperties(req, master);
        master.setEndDate(req.getEndDateInDate());
        master.setStatus("A");
        master.setCreateDate(new Date());
        master.setCreateUser(req.getUserId());
        return master;
    }

    private void validateReqData(EmployeeMasterReq req) {
        String endDate = req.getEndDate();
        Date date = CommonUtils.getDate(endDate, ApplicationConstant.DATE_FORMAT);
        if (date == null) {
            throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, ErrorResponseConstant.INVALID_DATE_MSG);
        }
        req.setEndDateInDate(date);
    }

    @Override
    public List<EmployeeMaster> getEmployeeMaster(Long orgId) {
        return empMasterRepository.findAllByStatusAndOrganizationId("A", orgId);
    }

    @Override
    public EmployeeMaster getEmployeeMasterById(CommonIdResponse id) {
        if (id.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<EmployeeMaster> byId = empMasterRepository.findById(id.getId());
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public CommonIdResponse updateMaster(EmployeeMasterReq req) {
        if (req.getEmployeeMasterId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        validateReqData(req);
        Optional<EmployeeMaster> byId = empMasterRepository.findById(req.getEmployeeMasterId());
        if (byId.isPresent()) {
            EmployeeMaster master = prepareMaster(req);
            master.setUpdateDate(new Date());
            master.setUpdateUser(req.getUserId());
            master.setId(byId.get().getId());
            master.setCreateDate(byId.get().getCreateDate());
            master.setCreateUser(byId.get().getCreateUser());
            Long id = empMasterRepository.saveAndFlush(master).getId();
            return new CommonIdResponse(id, req.getUserId());
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public void deleteEmpMaster(CommonIdResponse req) {
        if (req.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<EmployeeMaster> byId = empMasterRepository.findById(req.getId());
        if (byId.isPresent()) {
            EmployeeMaster master = byId.get();
            master.setStatus("D");
            master.setUpdateUser(req.getUserId());
            master.setUpdateDate(new Date());
            empMasterRepository.saveAndFlush(master);
            return;
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public CommonIdResponse saveMaster(LocatorMasterReq req) {
        if (req.getLocatorMasterId() != null) {
            req.setLocatorMasterId(null);
        }
        validateReqData(req);
        LocatorMaster master = prepareMaster(req);
        Long id = locatorMasterRepository.saveAndFlush(master).getId();
        return new CommonIdResponse(id, req.getUserId());
    }

    private LocatorMaster prepareMaster(LocatorMasterReq req) {
        LocatorMaster master = new LocatorMaster();
        BeanUtils.copyProperties(req, master);
        master.setStatus("A");
        master.setCreateDate(new Date());
        master.setCreateUser(req.getUserId());
        return master;
    }

    private void validateReqData(LocatorMasterReq req) {
        if (CommonUtils.isBlank(req.getLocation()) || CommonUtils.getLong(req.getLocation()) == null || !locationMasterRepository.findById(CommonUtils.getLong(req.getLocation())).isPresent()) {
            throw new ValidationException(ErrorResponseConstant.INVALID_LOCATION_CD, ErrorResponseConstant.INVALID_LOCATION_ID_MSG);
        }
        if (!masterDao.validateLocationIdAndOrgId(req.getLocation(), req.getOrgId())) {
            throw new ValidationException(ErrorResponseConstant.INVALID_LOCATION_ORG_CD, ErrorResponseConstant.INVALID_LOCATION_ORG_MSG);
        }
    }

    @Override
    public List<LocatorMaster> getLocatorMaster(Long orgId) {
        return masterDao.findAllLocatorByStatusAndOrgId("A", orgId);
    }

    @Override
    public LocatorMaster getLocatorMasterById(CommonIdResponse id) {
        if (id.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<LocatorMaster> byId = locatorMasterRepository.findById(id.getId());
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public CommonIdResponse updateMaster(LocatorMasterReq req) {
        if (req.getLocatorMasterId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        validateReqData(req);
        Optional<LocatorMaster> byId = locatorMasterRepository.findById(req.getLocatorMasterId());
        if (byId.isPresent()) {
            LocatorMaster master = prepareMaster(req);
            master.setUpdateDate(new Date());
            master.setUpdateUser(req.getUserId());
            master.setId(byId.get().getId());
            master.setCreateDate(byId.get().getCreateDate());
            master.setCreateUser(byId.get().getCreateUser());
            Long id = locatorMasterRepository.saveAndFlush(master).getId();
            return new CommonIdResponse(id, req.getUserId());
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public void deleteLocatorMaster(CommonIdResponse req) {
        if (req.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<LocatorMaster> byId = locatorMasterRepository.findById(req.getId());
        if (byId.isPresent()) {
            LocatorMaster master = byId.get();
            master.setStatus("D");
            master.setUpdateUser(req.getUserId());
            master.setUpdateDate(new Date());
            locatorMasterRepository.saveAndFlush(master);
            return;
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public CommonIdResponse saveMaster(UsersMasterReq req) {
        if (req.getUserId() != null) {
            req.setUserId(null);
        }

        if (CommonUtils.isBlank(req.getPassword()) || req.getPassword().length() < 6) {
            throw new ValidationException(ErrorResponseConstant.PWD_INVALID, ErrorResponseConstant.PWD_INVALID_MSG);
        }
        validateReqData(req);
        List<UsersMaster> allByUserCd = userMasterRepository.findAllByUserCd(req.getUserCd());
        if (allByUserCd.size() > 0) {
            throw new ValidationException(ErrorResponseConstant.USER_CD_EXIST_CD, ErrorResponseConstant.USER_CD_EXIST_MSG);
        }
        UsersMaster master = prepareMaster(req);
        Long id = userMasterRepository.saveAndFlush(master).getId();
        return new CommonIdResponse(id, req.getCreateUserId());
    }

    private UsersMaster prepareMaster(UsersMasterReq req) {
        UsersMaster master = new UsersMaster();
        BeanUtils.copyProperties(req, master);
        master.setPassword(passwordEncoder.encode(req.getPassword()));
        master.setEndDate(req.getEndDateInDate());
        master.setStatus("A");
        master.setCreateDate(new Date());
        master.setCreateUser(req.getCreateUserId());
        return master;
    }

    private void validateReqData(UsersMasterReq req) {
        String endDate = req.getEndDate();
        Date date = CommonUtils.getDate(endDate, ApplicationConstant.DATE_FORMAT);
        if (date == null) {
            throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, ErrorResponseConstant.INVALID_DATE_MSG);
        }
        req.setEndDateInDate(date);

        if (CommonUtils.isBlank(req.getUserType()) || !generalParamRepository.findById(new GeneralParameterId(req.getUserType(), GenParamTypeEnum.USER_TYPE.name())).isPresent()) {
            throw new ValidationException(ErrorResponseConstant.INVALID_USER_TYPE_CD, ErrorResponseConstant.INVALID_USER_TYPE_MSG);
        }
    }

    @Override
    public List<UsersMaster> getUserMaster() {
        return userMasterRepository.findAllByStatus("A");
    }

    @Override
    public UsersMaster getUserMasterById(CommonIdResponse id) {
        if (id.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<UsersMaster> byId = userMasterRepository.findById(id.getId());
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public CommonIdResponse updateMaster(UsersMasterReq req) {
        if (req.getUserId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        validateReqData(req);
        Optional<UsersMaster> byId = userMasterRepository.findById(req.getUserId());
        if (byId.isPresent()) {
            UsersMaster master = prepareMaster(req);
            master.setPassword(byId.get().getPassword());
            master.setUpdateDate(new Date());
            master.setUpdateUser(req.getCreateUserId());
            master.setId(byId.get().getId());
            master.setCreateDate(byId.get().getCreateDate());
            master.setCreateUser(byId.get().getCreateUser());
            master.setStatus("A");
            Long id = userMasterRepository.saveAndFlush(master).getId();
            return new CommonIdResponse(id, req.getCreateUserId());
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public void deleteUserMaster(CommonIdResponse req) {
        if (req.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<UsersMaster> byId = userMasterRepository.findById(req.getId());
        if (byId.isPresent()) {
            UsersMaster master = byId.get();
            master.setStatus("D");
            master.setUpdateUser(req.getUserId());
            master.setUpdateDate(new Date());
            userMasterRepository.saveAndFlush(master);
            return;
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public CommonIdResponse saveMaster(ItemMasterReq req) {
        if (req.getItemMasterId() != null) {
            req.setItemMasterId(null);
        }
        validateReqData(req);
        ItemCodeMaster master = prepareMaster(req);
        checkForDuplicateItemCode(master.getItemMasterCd(), master.getLocatorId());
        Long id = itemMasterRepository.saveAndFlush(master).getId();
        return new CommonIdResponse(id, req.getCreateUserId());
    }

    private void checkForDuplicateItemCode(String itemMasterCd, Long locatorId) {
        Optional<ItemCodeMaster> a = itemMasterRepository.findAllByItemMasterCdAndLocatorIdAndStatus(itemMasterCd, locatorId, "A");
        if (a.isPresent()) {
            throw new ValidationException(ErrorResponseConstant.ITEM_CD_EXIST_CD, ErrorResponseConstant.ITEM_CD_EXIST);
        }
    }

    private ItemCodeMaster prepareMaster(ItemMasterReq req) {
        ItemCodeMaster master = new ItemCodeMaster();
        BeanUtils.copyProperties(req, master);
        if (req.getUomId() == null || !uomMasterRepository.findById(req.getUomId()).isPresent()) {
            req.setUomId(2L);
        }
        master.setEndDate(req.getEndDateInDate());
        master.setStatus("A");
        master.setCreateDate(new Date());
        master.setCreateUser(req.getCreateUserId());

        if (CommonUtils.isBlank(req.getItemName())) {
            String itemMasterCd = saveAndGetNewItemNameCd(req);
            req.setItemName(itemMasterCd);
            master.setItemName(itemMasterCd);
        }

        String itemCode = generateItemCode(req);
        master.setItemMasterCd(itemCode);
        master.setInitQuantity(req.getQuantity());
        return master;
    }

    private String saveAndGetNewItemNameCd(ItemMasterReq req) {
        String itemNameCode = masterDao.getNextItemNameMasterCode(req);
        if (itemNameCode != null && itemNameCode.length() > 3) {
            throw new ValidationException(ErrorResponseConstant.ITEM_NAME_CD_MAX_LENGTH_CD, ErrorResponseConstant.ITEM_NAME_CD_MAX_LENGTH_MSG);
        }
        SaveItemNameReq request = new SaveItemNameReq();
        request.setOrgId(req.getOrgId());
        request.setCreateUser(req.getCreateUserId());
        request.setItemName(req.getItemMasterDesc());
        request.setItemNameCode(itemNameCode);
        request.setCategoryCode(req.getCategory());
        request.setDisciplineCode(req.getDisciplines());
        request.setSubCategoryCode(req.getSubCategory());
        request.setTypeCode(req.getType());
        generalParamsService.saveItemName(request);

        return itemNameCode;
    }

    private String generateItemCode(ItemMasterReq req) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(req.getCategory());
        stringBuilder.append(req.getSubCategory());
        stringBuilder.append(req.getType());
        stringBuilder.append(req.getDisciplines());
        stringBuilder.append(req.getItemName());
        stringBuilder.append(req.getBrandId());
        stringBuilder.append(req.getSize());
        stringBuilder.append(req.getColorId());
        stringBuilder.append(req.getUsageCategory());
        return stringBuilder.toString();
    }

    private void validateReqData(ItemMasterReq req) {
        String endDate = req.getEndDate();
        Date date = CommonUtils.getDate(endDate, ApplicationConstant.DATE_FORMAT);
        if (date == null) {
            throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, ErrorResponseConstant.INVALID_DATE_MSG);
        }
        req.setEndDateInDate(date);

        if (CommonUtils.isBlank(req.getCategory())
                || CommonUtils.isBlank(req.getSubCategory())
                || CommonUtils.isBlank(req.getType())
                || CommonUtils.isBlank(req.getDisciplines())
                || CommonUtils.isBlank(req.getBrandId())
                || CommonUtils.isBlank(req.getColorId())
                || CommonUtils.isBlank(req.getSize())
                || CommonUtils.isBlank(req.getUsageCategory())) {
            throw new ValidationException(ErrorResponseConstant.MANDATORY_FIELDS_CD, ErrorResponseConstant.MANDATORY_FIELDS_MSG);
        }

        if (req.getLocatorId() == null || !locatorMasterRepository.findById(req.getLocatorId()).isPresent()) {
            throw new ValidationException(ErrorResponseConstant.INVALID_LOCATOR_CD, ErrorResponseConstant.INVALID_LOCATOR_ID_MSG);
        }
        if (req.getUomId() == null || !uomMasterRepository.findById(req.getUomId()).isPresent()) {
            throw new ValidationException(ErrorResponseConstant.INVALID_UOM_CD, ErrorResponseConstant.INVALID_UOM_ID_MSG);
        }
        if (req.getLocationId() == null || !locationMasterRepository.findById(req.getLocationId()).isPresent()) {
            throw new ValidationException(ErrorResponseConstant.INVALID_LOCATION_CD, ErrorResponseConstant.INVALID_LOCATION_ID_MSG);
        }
        if (!masterDao.validateLocationIdAndLocatorIdAndOrgId(req.getLocationId(), req.getLocatorId(), req.getOrgId())) {
            throw new ValidationException(ErrorResponseConstant.INVALID_LOCATION_LOCATOR_ORG_CD, ErrorResponseConstant.INVALID_LOCATION_LOCATOR_ORG_MSG);
        }
    }

    @Override
    public List<ItemCodeMasterResp> getItemMaster(Long aLong) {
        List<ItemCodeMaster> a = masterDao.findAllItemMasterByStatusAndOrg("A", aLong);
        try {
            return prepareItemCodeResp(a);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<ItemCodeMasterResp> getItemMasterByOrgId(Long aLong) {
        List<ItemCodeMaster> a = masterDao.findAllItemMasterByStatusAndOrg("A", aLong);
        try {
            a = a.stream().distinct().collect(Collectors.toList());
            return prepareItemCodeResp(a);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<ItemCodeMasterResp> prepareItemCodeResp(List<ItemCodeMaster> a) {
        List<ItemCodeMasterResp> resp = new ArrayList<>();
        Map map = new HashMap<>();
        for (ItemCodeMaster aNew : a) {
            ItemCodeMasterResp r = new ItemCodeMasterResp();
            BeanUtils.copyProperties(aNew, r);
            r.setEndDateString(CommonUtils.formatDate(aNew.getEndDate()));

            Optional<GeneralParameter> byId1 = CachingUtil.fetchGeneralParamMaster(r.getCategory(), GenParamTypeEnum.CATEGORIES.name());
            if (!byId1.isPresent()) {
                byId1 = generalParamRepository.findById(new GeneralParameterId(r.getCategory(), GenParamTypeEnum.CATEGORIES.name()));
                if (byId1.isPresent())
                    CachingUtil.addGeneralParamMaster(r.getCategory(), GenParamTypeEnum.CATEGORIES.name(), byId1.get());
            }
            r.setCategoryDesc(byId1.isPresent() ? byId1.get().getParamDesc() : "");

            Optional<GeneralParameter> byId2 = CachingUtil.fetchGeneralParamMaster(r.getUsageCategory(), GenParamTypeEnum.USAGE_CAT.name());
            if (!byId2.isPresent()) {
                byId2 = generalParamRepository.findById(new GeneralParameterId(r.getUsageCategory(), GenParamTypeEnum.USAGE_CAT.name()));
                if (byId2.isPresent())
                    CachingUtil.addGeneralParamMaster(r.getUsageCategory(), GenParamTypeEnum.USAGE_CAT.name(), byId2.get());
            }
            r.setUsageCategoryDesc(byId2.isPresent() ? byId2.get().getParamDesc() : "");

            Optional<GeneralParameter> byId3 = CachingUtil.fetchGeneralParamMaster(r.getBrandId(), GenParamTypeEnum.BRANDS.name());
            if (!byId3.isPresent()) {
                byId3 = generalParamRepository.findById(new GeneralParameterId(r.getBrandId(), GenParamTypeEnum.BRANDS.name()));
                if (byId3.isPresent())
                    CachingUtil.addGeneralParamMaster(r.getBrandId(), GenParamTypeEnum.BRANDS.name(), byId3.get());
            }
            r.setBrandDesc(byId3.isPresent() ? byId3.get().getParamDesc() : "");

            Optional<GeneralParameter> byId4 = CachingUtil.fetchGeneralParamMaster(r.getColorId(), GenParamTypeEnum.COLORS.name());
            if (!byId4.isPresent()) {
                byId4 = generalParamRepository.findById(new GeneralParameterId(r.getColorId(), GenParamTypeEnum.COLORS.name()));
                if (byId4.isPresent())
                    CachingUtil.addGeneralParamMaster(r.getColorId(), GenParamTypeEnum.COLORS.name(), byId4.get());
            }
            r.setColorDesc(byId4.isPresent() ? byId4.get().getParamDesc() : "");

            Optional<GeneralParameter> byId5 = CachingUtil.fetchGeneralParamMaster(r.getSize(), GenParamTypeEnum.SIZES.name());
            if (!byId5.isPresent()) {
                byId5 = generalParamRepository.findById(new GeneralParameterId(r.getSize(), GenParamTypeEnum.SIZES.name()));
                if (byId5.isPresent())
                    CachingUtil.addGeneralParamMaster(r.getSize(), GenParamTypeEnum.SIZES.name(), byId5.get());
            }
            r.setSizeDesc(byId5.isPresent() ? byId5.get().getParamDesc() : "");

            List<ItemTypeMaster> itemType = CachingUtil.fetchItemTypeMaster(r.getCategory(), r.getSubCategory(), r.getType());
            if (itemType == null) {
                itemType = itemTypeRepository.findAllByCategoryCodeAndSubCategoryCodeAndTypeCode(r.getCategory(), r.getSubCategory(), r.getType());
                CachingUtil.addItemTypeMaster(r.getCategory(), r.getSubCategory(), r.getType(), itemType);
            }
            r.setTypeDesc(itemType.size() > 0 ? itemType.get(0).getTypeDesc() : "");

            List<DisciplineMaster> discType = CachingUtil.fetchDisciplineMaster(r.getCategory(), r.getSubCategory(), r.getType(), r.getDisciplines());
            if (discType == null) {
                discType = disciplineRepository.findAllByCategoryCodeAndSubCategoryCodeAndTypeCodeAndDisciplineCode(r.getCategory(), r.getSubCategory(), r.getType(), r.getDisciplines());
                CachingUtil.addDisciplineMaster(r.getCategory(), r.getSubCategory(), r.getType(), r.getDisciplines(), discType);
            }
            r.setDisciplinesDesc(discType.size() > 0 ? discType.get(0).getDisciplineName() : "");

            List<SubCategoryMaster> subCat = CachingUtil.fetchSubCategoryMaster(r.getCategory(), r.getSubCategory());
            if (subCat == null) {
                subCat = subCategoryRepository.findAllByCategoryCodeAndSubCategoryCode(r.getCategory(), r.getSubCategory());
                CachingUtil.addSubCategoryMaster(r.getCategory(), r.getSubCategory(), subCat);
            }
            r.setSubCategoryDesc(subCat.size() > 0 ? subCat.get(0).getSubCategoryDesc() : "");
            if (map.containsKey(aNew.getUomId())) {
                UOMMaster uom = (UOMMaster) map.get(aNew.getUomId());
                r.setUomDtls(uom);
            } else {
                Optional<UOMMaster> byId = uomMasterRepository.findById(aNew.getUomId());
                if (byId.isPresent()) {
                    map.put(aNew.getUomId(), byId.get());
                    r.setUomDtls(byId.get());
                }
            }
            resp.add(r);
        }

        if (!resp.isEmpty()) {
            resp = resp.stream().distinct().collect(Collectors.toList());
        }
        return resp;
    }

    @Override
    public Object getItemMasterById(CommonIdResponse id) {
        if (id.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<ItemCodeMaster> byId = itemMasterRepository.findById(id.getId());
        if (byId.isPresent()) {
            ItemCodeMaster itemCodeMaster = byId.get();
            List l = new ArrayList();
            l.add(itemCodeMaster);
            List list = prepareItemCodeResp(l);
            if (list.size() > 0) {
                return list.get(0);
            }
            return null;
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    public CommonIdResponse updateMaster(ItemMasterReq req) {
        if (CommonUtils.isBlank(req.getItemMasterCd())) {
            throw new ValidationException(ErrorResponseConstant.INVALID_ITEM_ID_CD, ErrorResponseConstant.INVALID_ITEM_ID_MSG);
        }
        if (CommonUtils.isBlank(req.getLocatorId())) {
            throw new ValidationException(ErrorResponseConstant.INVALID_LOCATOR_CD, ErrorResponseConstant.INVALID_LOCATOR_ID_MSG);
        }
        validateReqData(req);
        Optional<ItemCodeMaster> byId = itemMasterRepository.findAllByItemMasterCdAndLocatorIdAndStatus(req.getItemMasterCd(), req.getLocatorId(), "A");
        if (byId.isPresent()) {
            ItemCodeMaster master = prepareMaster(req);
            master.setUpdateDate(new Date());
            master.setUpdateUser(req.getCreateUserId());
            master.setId(byId.get().getId());
            master.setStatus(byId.get().getStatus());
            master.setCreateDate(byId.get().getCreateDate());
            master.setCreateUser(byId.get().getCreateUser());
            master.setItemMasterCd(byId.get().getItemMasterCd());
            master.setInitQuantity(byId.get().getInitQuantity());
            Long id = itemMasterRepository.saveAndFlush(master).getId();
            return new CommonIdResponse(id, req.getCreateUserId());
        }
        throw new ValidationException(ErrorResponseConstant.INVALID_ITEM_ID_CD, ErrorResponseConstant.INVALID_ITEM_ID_MSG);
    }

    @Override
    public void deleteItemMaster(CommonIdResponse req) {
        if (req.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<ItemCodeMaster> byId = itemMasterRepository.findById(req.getId());
        if (byId.isPresent()) {
            ItemCodeMaster master = byId.get();
            master.setStatus("D");
            master.setUpdateUser(req.getUserId());
            master.setUpdateDate(new Date());
            itemMasterRepository.saveAndFlush(master);
            return;
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    @Override
    @Transactional
    public ItemCodeMaster saveMasterNewEntry(String itemCode, Long locatorId, String createUser) {
        checkForDuplicateItemCode(itemCode, locatorId);

        List<ItemCodeMaster> a = itemMasterRepository.findAllByItemMasterCdAndStatus(itemCode, "A");
        if (!a.isEmpty()) {
            ItemCodeMaster itemCodeMasterOrg = a.get(0);
            ItemCodeMaster itemCodeMaster = new ItemCodeMaster();
            BeanUtils.copyProperties(itemCodeMasterOrg, itemCodeMaster);
            itemCodeMaster.setId(null);
            itemCodeMaster.setQuantity(0D);
            itemCodeMaster.setInitQuantity(0D);
            itemCodeMaster.setLocatorId(locatorId);
            Optional<LocatorMaster> byId = locatorMasterRepository.findById(locatorId);
            if (byId.isPresent()) {
                itemCodeMaster.setLocationId(CommonUtils.getLong(byId.get().getLocation()));
            } else {
                throw new ValidationException(ErrorResponseConstant.INVALID_LOCATOR_CD, ErrorResponseConstant.INVALID_LOCATOR_ID_MSG);
            }
            itemCodeMaster.setCreateDate(new Date());
            itemCodeMaster.setCreateUser(createUser);
            ItemCodeMaster itemCodeMasterUpdate = itemMasterRepository.saveAndFlush(itemCodeMaster);
            return itemCodeMasterUpdate;
        } else {
            throw new ValidationException(ErrorResponseConstant.ITEM_CD_NOT_EXIST_CD, ErrorResponseConstant.ITEM_CD_NOT_EXIST);
        }
    }

    @Override
    public Object changePassword(ChangePasswordReq changePasswordReq) {
        if (CommonUtils.isBlank(changePasswordReq.getUserCd())) {
            throw new ValidationException(ErrorResponseConstant.INVALID_USER_ID, ErrorResponseConstant.INVALID_USER_MSG);
        }
        if (CommonUtils.isBlank(changePasswordReq.getNewPassword()) || changePasswordReq.getNewPassword().length() < 6) {
            throw new ValidationException(ErrorResponseConstant.PWD_INVALID, ErrorResponseConstant.PWD_INVALID_MSG);
        }
        List<UsersMaster> allByUserCd = userMasterRepository.findAllByUserCd(changePasswordReq.getUserCd());
        if (allByUserCd.isEmpty()) {
            throw new ValidationException(ErrorResponseConstant.INVALID_USER_ID, ErrorResponseConstant.INVALID_USER_MSG);
        }
        /*if (!SecurityUtils.hashPassword(allByUserCd.get(0).getPassword()).equalsIgnoreCase(changePasswordReq.getOldPassword())) {

        }*/
        String newPassHash = passwordEncoder.encode(changePasswordReq.getNewPassword());
        UsersMaster usersMaster = allByUserCd.get(0);
        usersMaster.setPassword(newPassHash);
        usersMaster.setChangePasswordDate(new Date());
        usersMaster.setChangePasswordBy(changePasswordReq.getUserCd());
        usersMaster.setStatus("A");
        userMasterRepository.save(usersMaster);
        return null;
    }

    @Override
    public Object getItemMasterByItemCode(FetchItemMasterCode req) {
        List<ItemCodeMaster> a = itemMasterRepository.findAllByItemMasterCd(req.getItemCode());
        return prepareItemCodeResp(a);
    }

    @Override
    public Object getOrgMasterAndLocationById(CommonIdResponse id) {
        if (id.getId() == null) {
            throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
        }
        Optional<OrganizationMaster> byId = organizationMasterRepository.findById(id.getId());
        if (byId.isPresent()) {
            OrganizationMaster organizationMaster = byId.get();
            OrganizationMstResponse response = prepareOrgMasterResp(organizationMaster, id.getUserId());
            return response;
        }
        throw new ValidationException(ErrorResponseConstant.ID_NOT_FOUND_CD, ErrorResponseConstant.ID_NOT_FOUND_MSG);
    }

    private OrganizationMstResponse prepareOrgMasterResp(OrganizationMaster orgMasterById, String user) {
        OrganizationMstResponse response = new OrganizationMstResponse();
        BeanUtils.copyProperties(orgMasterById, response);
        LocationMaster locationMasterById = null;
        if (orgMasterById != null && orgMasterById.getLocationId() != null) {
            RequestByIdReq requestByIdReq = new RequestByIdReq();
            requestByIdReq.setRequestId(orgMasterById.getLocationId());
            requestByIdReq.setUserId(user);
            locationMasterById = getLocationMasterById(requestByIdReq);
            response.setLocationDetails(locationMasterById);
        }
        return response;
    }

    @Override
    public Object getAllOHQ(OHQRequest id) {
        List<OHQDataEntity> ohqData = masterDao.getAlllOHQData(id);
        List<OHQMainResponse> response = prepareOHQResponse(ohqData, true);
        return response;
    }

    @Override
    public Object getLocatorMasterByOrgId(OrgIdRequest id) {
        if (id.getOrgId() == null) {
            throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
        }

        return masterDao.getLocatorDtlsByOrgId(id.getOrgId());
    }

    @Override
    public Object getLocationMasterByOrgId(OrgIdRequest id) {
        if (id.getOrgId() == null) {
            throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
        }

        return locationMasterRepository.findAllByStatusAndOrgId("A", id.getOrgId());
    }

    @Override
    public Object validateDuplicateItemName(ValidateItemNameReq req) {

        List<ItemCodeMaster> itemCodeMasterAll = masterDao.getItemMasterByDesc(req.getItemName());
        if (itemCodeMasterAll == null || itemCodeMasterAll.isEmpty()) {
            throw new ValidationException(ErrorResponseConstant.ITEM_CD_NOT_EXIST_CD, ErrorResponseConstant.ITEM_CD_NOT_EXIST);
        }

        // List<ItemCodeMaster> itemCodeMasters = masterDao.getItemMasterByDescAndOrgId(req.getItemName(), req.getOrgId());
        // if (itemCodeMasters != null && !itemCodeMasters.isEmpty()) {
        //     throw new ValidationException(ErrorResponseConstant.ITEM_CD_EXIST_CD, ErrorResponseConstant.ITEM_CD_EXIST);
        // }

        ItemCodeMasterResp r =  null;
        if (itemCodeMasterAll != null && !itemCodeMasterAll.isEmpty()) {
            r = new ItemCodeMasterResp();
            ItemCodeMaster aNew = itemCodeMasterAll.get(0);
            BeanUtils.copyProperties(aNew, r);
            r.setEndDateString(CommonUtils.formatDate(aNew.getEndDate()));
        }

        return r;
    }
}