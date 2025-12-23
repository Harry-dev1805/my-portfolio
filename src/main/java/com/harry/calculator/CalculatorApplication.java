package com.harry.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}

}

//annotation
@RestController
class CalculatorController {
	@GetMapping("/hello")
	public String Hello() {return "Hello world from Spring boot";}

	@GetMapping("/add")
	public CalculationResult addTwoNumbers(@RequestParam(name = "a")double num1, @RequestParam(name = "b")double num2) {
		CalculationResult calculationResult = new CalculationResult(num1, num2, "+");
		calculationResult.calculator();
		return calculationResult;
	}

	@GetMapping("/sub")
	public CalculationResult subTwoNumbers(@RequestParam(name = "a")double num1, @RequestParam(name = "b")double num2) {
		CalculationResult calculationResult = new CalculationResult(num1, num2, "-");
		calculationResult.calculator();
		return calculationResult;
	}

	@GetMapping("/multi")
	public CalculationResult multiTwoNumbers(@RequestParam(name = "a")double num1, @RequestParam(name = "b")double num2) {
		CalculationResult calculationResult = new CalculationResult(num1, num2, "*");
		calculationResult.calculator();
		return calculationResult;
	}

	@GetMapping("/divide")
	public CalculatorDTO divideTwoNumbers(@RequestParam(name = "a")double num1, @RequestParam(name = "b")double num2) {
		try {
			CalculationResult calculationResult = new CalculationResult(num1, num2, "/");
			calculationResult.calculator();
			return new CalculatorDTO("Calculator success", calculationResult);
		} catch (Error e){
			return new CalculatorDTO(e.getMessage());
		}
	}
}

class CalculatorDTO {
	public String message;
	public CalculationResult data;

	public CalculatorDTO(String message){
		this.message = message;
	}

	public CalculatorDTO(String message, CalculationResult data){
		this.message = message;
		this.data = data;
	}
}

class CalculationResult {
	public double num1;
	private double num2;
	private String operation;
	private double result;

	public CalculationResult(double num1, double num2, String operation) {
		this.num1 = num1;
		this.num2 = num2;
		this.operation = operation;
	}

	public void calculator(){
		switch (this.operation){
			case "+":
				this.result = this.num1 + this.num2;
				break;
			case "-":
				this.result = this.num1 - this.num2;
				break;
			case "*":
				this.result = this.num1 * this.num2;
				break;
			case "/":
				if (this.num2 == 0) {
					throw new Error("Cannot division with 0");
				}
				this.result = this.num1 / this.num2;
				break;
			default:
				throw new Error("Operation not found");
		}
	}

	public double getNum1() {return num1;}
	public double getNum2() {return num2;}
	public String getOperation() {return operation;}
	public double getResult() {return result;}
}


