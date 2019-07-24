package pl.com.itti.kafkaproducer.model;

public class NetFlowFrame {

    private String startTime;
    private String dur;
    private String proto;
    private String srcAddr;
    private String sport;
    private String dir;
    private String dstAddr;
    private String dPort;
    private String state;
    private String sTos;
    private String dTos;
    private String totPkts;
    private String totBytes;
    private String srcBytes;
    private String label;

    public String getStartTime() {
        return startTime;
    }

    public String getDur() {
        return dur;
    }

    public String getProto() {
        return proto;
    }

    public String getSrcAddr() {
        return srcAddr;
    }

    public String getSport() {
        return sport;
    }

    public String getDir() {
        return dir;
    }

    public String getDstAddr() {
        return dstAddr;
    }

    public String getdPort() {
        return dPort;
    }

    public String getState() {
        return state;
    }

    public String getsTos() {
        return sTos;
    }

    public String getdTos() {
        return dTos;
    }

    public String getTotPkts() {
        return totPkts;
    }

    public String getTotBytes() {
        return totBytes;
    }

    public String getSrcBytes() {
        return srcBytes;
    }

    public String getLabel() {
        return label;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setDur(String dur) {
        this.dur = dur;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }

    public void setSrcAddr(String srcAddr) {
        this.srcAddr = srcAddr;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setDstAddr(String dstAddr) {
        this.dstAddr = dstAddr;
    }

    public void setdPort(String dPort) {
        this.dPort = dPort;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setsTos(String sTos) {
        this.sTos = sTos;
    }

    public void setdTos(String dTos) {
        this.dTos = dTos;
    }

    public void setTotPkts(String totPkts) {
        this.totPkts = totPkts;
    }

    public void setTotBytes(String totBytes) {
        this.totBytes = totBytes;
    }

    public void setSrcBytes(String srcBytes) {
        this.srcBytes = srcBytes;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "NetFlowFrame{" +
                "startTime='" + startTime + '\'' +
                ", dur='" + dur + '\'' +
                ", proto='" + proto + '\'' +
                ", srcAddr='" + srcAddr + '\'' +
                ", sport='" + sport + '\'' +
                ", dir='" + dir + '\'' +
                ", dstAddr='" + dstAddr + '\'' +
                ", dPort='" + dPort + '\'' +
                ", state='" + state + '\'' +
                ", sTos='" + sTos + '\'' +
                ", dTos='" + dTos + '\'' +
                ", totPkts='" + totPkts + '\'' +
                ", totBytes='" + totBytes + '\'' +
                ", srcBytes='" + srcBytes + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
