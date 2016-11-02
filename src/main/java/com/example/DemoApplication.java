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
    
    float suma(float A, float B){
    	return A + B;
    }
    
    float resta(float A, float B){
    	return A - B;
    }
    
    float mult(float A, float B){
    	return A * B;
    }
    
    float div(float A, float B){
    	return A / B;
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
    	
    	switch(op){
	    	case "suma":
	    		return a + " + " + b + " = " + suma(A, B);
	    	case "resta":
	    		return a + " - " + b + " = " + resta(A, B);
	    	case "mult":
	    		return a + " x " + b + " = " + mult(A, B);
	    	case "div":
	    		if( B != 0 ){
	    			return a + " / " + b + " = " + div(A, B);
	    		}
	    		return "B no puede ser cero (0)";
	    	default:
	    		return "Ingrese la operación que desea realizar";
    	}
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
    }
}
