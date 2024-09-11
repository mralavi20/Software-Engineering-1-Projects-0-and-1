package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class DemoApplication {


	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	Bank bank;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public String check_command (String command) {
		String message;
		String[] command_words = command.split (" ", 5);

		if (command_words[0].equals ("DEPOSIT")) {
			message = bank.deposit (Integer.parseInt (command_words[2]), command_words[1]);
		}
		else if (command_words[0].equals ("WITHDRAW")) {
			message = bank.withdraw (Integer.parseInt (command_words[2]), command_words[1]);
		}
		else {
			message = bank.show_balance (command_words[1]);
		}

		return message;
	}

	@JmsListener(destination = "INQ")
	public void receive_message (String message) {
		String response = check_command (message);
		send_message (response);
	}

	public void send_message (String message) {
		jmsTemplate.convertAndSend("OUTQ", message);
	}
}
