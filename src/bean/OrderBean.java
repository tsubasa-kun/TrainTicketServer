package bean;

/**
 * 订单实体类
 * 
 * @author cookie
 * 
 */
public class OrderBean extends ResultBean {
	private int id;//ID
	private String orderId;//订单ID
    private String account;//账户
    private String trainNo;//车次
    private String fromStation;//出发地
    private String startTime;//出发时间
    private String toStation;//目的地
    private String endTime;//到站时间
    private String date;//日期
    private String seat;//席别
    private String carriage;//车厢
    private String seatNo;// 座位号
    private String money;//票价
    private String type;//车票类型

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
