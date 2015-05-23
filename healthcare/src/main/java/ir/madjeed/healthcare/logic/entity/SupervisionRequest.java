package ir.madjeed.healthcare.logic.entity;

public interface SupervisionRequest {
    public User getPatient();
    public void setPatient(User u);
    public User getDoctor();
    public void setDoctor(User u);
    public String getStatus(); // accepted, rejected or pending
    public void setStatus(String status);
    public String getType(); // refer or request
    public void setType(String type);
    public String getRequestDetail();
    public void setRequestDetail(String detail);
    public String getRequestAnswer();
    public void setRequestAnswer(String answer);
}
