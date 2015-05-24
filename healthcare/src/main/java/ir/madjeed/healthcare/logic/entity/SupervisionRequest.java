package ir.madjeed.healthcare.logic.entity;

import ir.madjeed.healthcare.logic.entity.impl.persistent.UserPersistent;

public interface SupervisionRequest {
    public UserPersistent getPatient();
    public void setPatient(UserPersistent u);
    public UserPersistent getDoctor();
    public void setDoctor(UserPersistent u);
    public String getStatus(); // accepted, rejected or pending
    public void setStatus(String status);
    public String getType(); // refer or request
    public void setType(String type);
    public String getRequestDetail();
    public void setRequestDetail(String detail);
    public String getRequestAnswer();
    public void setRequestAnswer(String answer);
}
