package com.iolll.nicesome.model.entity;


//      {field: 'number', title: '序号', width:60, fixed: 'left'}
//              ,{field: 'remark', title: '备注', width:60}
//              ,{field: 'order', title: '订单号', width:80}
//              ,{field: 'hotel', title: '酒店名称', width: 120}
//              ,{field: 'status', title: '订单状态', width: 120}
//              ,{field: 'experience', title: '审批状态', width: 120}
//              ,{field: 'payment', title: '支付状态', width: 120}
//              ,{field: 'serve', title: '服务编号', width: 120}
//              ,{field: 'client', title: '客户名称', width: 120}
//              ,{field: 'id', title: '入住人', width:80}
//              ,{field: 'begin', title: '入住日期', width:120}
//              ,{field: 'end', title: '离店日期', width:120}
//              ,{field: 'rooms', title: '房间数', width:80}
//              ,{field: 'room', title: '间夜数', width: 80}
//              ,{field: 'experience', title: '销售价', width: 80}
//              ,{field: 'score', title: '会员卡号', width: 120}
//              ,{field: 'reserve', title: '预订人', width: 80}
//              ,{field: 'reservation', title: '订房人', width: 80}
public class Order {
    private long number;
    private String remark;
    private String order;
    private String hotel;
    private String status = "订单状态";
    private String experience = "审批状态";
    private String payment = "支付状态";
    private String serve = "服务编号";
    private String client = "客户名称";
    private String id = "客户id";
    private String begin ="入住时间";
    private String end="离店时间";
    private String rooms="房间数";
    private String room="间夜数";
    private String experiencePrice="销售价";
    private String score="会员卡号";
    private String reserve="预订人";
    private String reservation="订房人";


    public Order(int i, String s, String s1, String s2) {
        number = i;
        remark = s;
        order = s1;
        hotel = s2;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getServe() {
        return serve;
    }

    public void setServe(String serve) {
        this.serve = serve;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getExperiencePrice() {
        return experiencePrice;
    }

    public void setExperiencePrice(String experiencePrice) {
        this.experiencePrice = experiencePrice;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", remark='" + remark + '\'' +
                ", order='" + order + '\'' +
                ", hotel='" + hotel + '\'' +
                ", status='" + status + '\'' +
                ", experience='" + experience + '\'' +
                ", payment='" + payment + '\'' +
                ", serve='" + serve + '\'' +
                ", client='" + client + '\'' +
                ", id='" + id + '\'' +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                ", rooms='" + rooms + '\'' +
                ", room='" + room + '\'' +
                ", experiencePrice='" + experiencePrice + '\'' +
                ", score='" + score + '\'' +
                ", reserve='" + reserve + '\'' +
                ", reservation='" + reservation + '\'' +
                '}';
    }
}
