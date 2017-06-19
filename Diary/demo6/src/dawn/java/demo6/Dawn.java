package dawn.java.demo6;

import java.math.BigDecimal;

public class Dawn {
	//属性声明
	private String firstNum = "0";
	
	private String secondNum = "0";
	
	private char operator = '+';
	
	private String result = "0";
	//构造
	public Dawn(){}
	
	public Dawn(String firstNum,String secondNum,char operator,String result){
		this.firstNum = firstNum;
		this.secondNum = secondNum;
		this.operator = operator;
		this.result = result;
	}

	//get、set方法
	public String getFirstNum() {
		return firstNum;
	}

	public String getSecondNum() {
		return secondNum;
	}
	
	public char getOperator() {
		return operator;
	}

	public String getResult() {
		return result;
	}
	
	public void setFirstNum(String firstNum) {
		this.firstNum = firstNum;
	}

	public void setSecondNum(String secondNum) {
		this.secondNum = secondNum;
	}
	
	public void setOperator(char operator) {
		this.operator = operator;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	public void calculate() {
        BigDecimal a = new BigDecimal(this.firstNum);
        BigDecimal b = new BigDecimal(this.secondNum);
        switch (this.operator) {
        case '+':
            this.result = a.add(b).toString();
            break;
        case '-':
            this.result = a.subtract(b).toString();
            break;
        case '*':
            this.result = a.multiply(b).toString();
            break;
        case '/':
            if (b.doubleValue()==0) {
                throw new RuntimeException("被除数不能为零");
            }
            this.result = a.divide(b,10,BigDecimal.ROUND_HALF_DOWN).toString();
            break;
        default:
            break;
        }
	}
}
