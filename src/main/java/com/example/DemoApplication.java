package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class DemoApplication {
	
	public abstract class calculator{
		public String txt; 
		public calculator(String str){
			txt = str;
		}
		public abstract float calculate(float A, float B);
	}
	
	public class suma extends calculator{
		
		public suma(String str) {
			super(str);
		}

		public float calculate(float A, float B){
			return A + B;
		}
	}
	public class resta extends calculator{
		public resta(String str) {
			super(str);
		}

		public float calculate(float A, float B){
			return A - B;
		}
	}
	public class mult extends calculator{
		public mult(String str) {
			super(str);
		}
		public float calculate(float A, float B){
			return A * B;
		}
	}
	public class div extends calculator{
		public div(String str) {
			super(str);
		}
		public float calculate(float A, float B){
			return A / B;
		}
	}
	
	
	public String operate(String op, float A, float B){
		calculator calc = identify(op, A, B);
		if ( calc != null ){
			return calc.txt + calc.calculate(A, B);
		}
		return "Datos inválidos";
	}
	
	public calculator identify(String op, float A, float B){
		switch(op){
		   	case "suma":
		   		return new suma(A + " + " + B + " = ");
		   	case "resta":
		   		return new resta(A + " - " + B + " = ");
		   	case "mult":
		   		return new mult(A + " * " + B + " = ");
		   	case "div":
		   		if( B != 0 ){
		   			return new div(A + " / " + B + " = ");
		   		}
		   		return null;
		   	default:
		   		return null;
		}
	}
	
    @RequestMapping("/")
    @ResponseBody
    String home(){
    	return "El uso es:  /calc?a=1&b=2&op=div   Donde:\n"
    			+ "a es el primer número\n"
    			+ "b el segundo numero\n"
    			+ "op es la operación que desea realizar.";
    }
    
	@RequestMapping("/calc")
    @ResponseBody
    String calc(@RequestParam String a, @RequestParam String b, @RequestParam String op) {
    	
    	float A = 0;
    	float B = 0;
    	try{
    		A = Float.parseFloat(a);
        	B = Float.parseFloat(b);
    	}catch( IllegalArgumentException e ){
    		return "Ingrese los valores adecuados";
    	}
    	
    	return operate(op, A, B);    	
    	
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
    }
}
