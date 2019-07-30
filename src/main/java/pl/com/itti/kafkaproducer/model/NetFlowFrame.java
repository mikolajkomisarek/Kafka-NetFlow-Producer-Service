package pl.com.itti.kafkaproducer.model;

public class NetFlowFrame {

    private String srcAddr;
    private String dstAddr;
    private String proto;
    private String sPort;
    private String dPort;
    private String state;
    private String sTos;
    private String dTos;
    private String srcWin;
    private String dstWin;
    private String sHops;
    private String dHops;
    private String startTime;
    private String lastTime;
    private String sTtl;
    private String dTtl;
    private String tcpRtt;
    private String synAck;
    private String ackDat;
    private String srcPkts;
    private String dstPkts;
    private String rcBytes;
    private String dstBytes;
    private String sAppBytes;
    private String dAppBytes;
    private String dur;
    private String totPkts;
    private String totBytes;
    private String totAppByte;
    private String rate;
    private String srcRate;
    private String dstRate;
    private String label;

    public NetFlowFrame(String[] netFlowLine) {
        this.srcAddr = netFlowLine[0];
        this.dstAddr = netFlowLine[1];
        this.proto = netFlowLine[2];
        this.sPort = netFlowLine[3];
        this.dPort = netFlowLine[4];
        this.state = netFlowLine[5];
        this.sTos = netFlowLine[6];
        this.dTos = netFlowLine[7];
        this.srcWin = netFlowLine[8];
        this.dstWin = netFlowLine[9];
        this.sHops = netFlowLine[10];
        this.dHops = netFlowLine[11];
        this.startTime = netFlowLine[12];
        this.lastTime = netFlowLine[13];
        this.sTtl = netFlowLine[14];
        this.dTtl = netFlowLine[15];
        this.tcpRtt = netFlowLine[16];
        this.synAck = netFlowLine[17];
        this.ackDat = netFlowLine[18];
        this.srcPkts = netFlowLine[19];
        this.dstPkts = netFlowLine[20];
        this.rcBytes = netFlowLine[21];
        this.dstBytes = netFlowLine[22];
        this.sAppBytes = netFlowLine[23];
        this.dAppBytes = netFlowLine[24];
        this.dur = netFlowLine[25];
        this.totPkts = netFlowLine[26];
        this.totBytes = netFlowLine[27];
        this.totAppByte = netFlowLine[28];
        this.rate = netFlowLine[29];
        this.srcRate = netFlowLine[30];
        this.dstRate = netFlowLine[31];
        this.label =netFlowLine[32];
    }

    public String getSrcAddr() {
        return srcAddr;
    }

    public void setSrcAddr(String srcAddr) {
        this.srcAddr = srcAddr;
    }

    public String getDstAddr() {
        return dstAddr;
    }

    public void setDstAddr(String dstAddr) {
        this.dstAddr = dstAddr;
    }

    public String getProto() {
        return proto;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }

    public String getsPort() {
        return sPort;
    }

    public void setsPort(String sPort) {
        this.sPort = sPort;
    }

    public String getdPort() {
        return dPort;
    }

    public void setdPort(String dPort) {
        this.dPort = dPort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getsTos() {
        return sTos;
    }

    public void setsTos(String sTos) {
        this.sTos = sTos;
    }

    public String getdTos() {
        return dTos;
    }

    public void setdTos(String dTos) {
        this.dTos = dTos;
    }

    public String getSrcWin() {
        return srcWin;
    }

    public void setSrcWin(String srcWin) {
        this.srcWin = srcWin;
    }

    public String getDstWin() {
        return dstWin;
    }

    public void setDstWin(String dstWin) {
        this.dstWin = dstWin;
    }

    public String getsHops() {
        return sHops;
    }

    public void setsHops(String sHops) {
        this.sHops = sHops;
    }

    public String getdHops() {
        return dHops;
    }

    public void setdHops(String dHops) {
        this.dHops = dHops;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getsTtl() {
        return sTtl;
    }

    public void setsTtl(String sTtl) {
        this.sTtl = sTtl;
    }

    public String getdTtl() {
        return dTtl;
    }

    public void setdTtl(String dTtl) {
        this.dTtl = dTtl;
    }

    public String getTcpRtt() {
        return tcpRtt;
    }

    public void setTcpRtt(String tcpRtt) {
        this.tcpRtt = tcpRtt;
    }

    public String getSynAck() {
        return synAck;
    }

    public void setSynAck(String synAck) {
        this.synAck = synAck;
    }

    public String getAckDat() {
        return ackDat;
    }

    public void setAckDat(String ackDat) {
        this.ackDat = ackDat;
    }

    public String getSrcPkts() {
        return srcPkts;
    }

    public void setSrcPkts(String srcPkts) {
        this.srcPkts = srcPkts;
    }

    public String getDstPkts() {
        return dstPkts;
    }

    public void setDstPkts(String dstPkts) {
        this.dstPkts = dstPkts;
    }

    public String getRcBytes() {
        return rcBytes;
    }

    public void setRcBytes(String rcBytes) {
        this.rcBytes = rcBytes;
    }

    public String getDstBytes() {
        return dstBytes;
    }

    public void setDstBytes(String dstBytes) {
        this.dstBytes = dstBytes;
    }

    public String getsAppBytes() {
        return sAppBytes;
    }

    public void setsAppBytes(String sAppBytes) {
        this.sAppBytes = sAppBytes;
    }

    public String getdAppBytes() {
        return dAppBytes;
    }

    public void setdAppBytes(String dAppBytes) {
        this.dAppBytes = dAppBytes;
    }

    public String getDur() {
        return dur;
    }

    public void setDur(String dur) {
        this.dur = dur;
    }

    public String getTotPkts() {
        return totPkts;
    }

    public void setTotPkts(String totPkts) {
        this.totPkts = totPkts;
    }

    public String getTotBytes() {
        return totBytes;
    }

    public void setTotBytes(String totBytes) {
        this.totBytes = totBytes;
    }

    public String getTotAppByte() {
        return totAppByte;
    }

    public void setTotAppByte(String totAppByte) {
        this.totAppByte = totAppByte;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSrcRate() {
        return srcRate;
    }

    public void setSrcRate(String srcRate) {
        this.srcRate = srcRate;
    }

    public String getDstRate() {
        return dstRate;
    }

    public void setDstRate(String dstRate) {
        this.dstRate = dstRate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "NetFlowFrame{" +
                "srcAddr='" + srcAddr + '\'' +
                ", dstAddr='" + dstAddr + '\'' +
                ", proto='" + proto + '\'' +
                ", sPort='" + sPort + '\'' +
                ", dPort='" + dPort + '\'' +
                ", state='" + state + '\'' +
                ", sTos='" + sTos + '\'' +
                ", dTos='" + dTos + '\'' +
                ", srcWin='" + srcWin + '\'' +
                ", dstWin='" + dstWin + '\'' +
                ", sHops='" + sHops + '\'' +
                ", dHops='" + dHops + '\'' +
                ", startTime='" + startTime + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", sTtl='" + sTtl + '\'' +
                ", dTtl='" + dTtl + '\'' +
                ", tcpRtt='" + tcpRtt + '\'' +
                ", synAck='" + synAck + '\'' +
                ", ackDat='" + ackDat + '\'' +
                ", srcPkts='" + srcPkts + '\'' +
                ", dstPkts='" + dstPkts + '\'' +
                ", rcBytes='" + rcBytes + '\'' +
                ", dstBytes='" + dstBytes + '\'' +
                ", sAppBytes='" + sAppBytes + '\'' +
                ", dAppBytes='" + dAppBytes + '\'' +
                ", dur='" + dur + '\'' +
                ", totPkts='" + totPkts + '\'' +
                ", totBytes='" + totBytes + '\'' +
                ", totAppByte='" + totAppByte + '\'' +
                ", rate='" + rate + '\'' +
                ", srcRate='" + srcRate + '\'' +
                ", dstRate='" + dstRate + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
