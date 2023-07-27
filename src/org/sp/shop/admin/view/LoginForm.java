package org.sp.shop.admin.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.sp.shop.admin.domain.Admin;
import org.sp.shop.admin.model.AdminDAO;

public class LoginForm extends JFrame{
	AdminMain adminMain;
	JTextField t_id;
	JPasswordField t_pass;
	JButton bt;
	
	//DAO를 이용하여 db업무를 수행하므로 DAO를 보유하자
	AdminDAO adminDAO;
	Admin adminDTO;
	
	public LoginForm(AdminMain adminMain) {
		super("관리자 로그인");
		this.adminMain=adminMain;//메인 프레임 넘겨받기
		
		t_id=new JTextField();
		t_pass=new JPasswordField();
		bt=new JButton("Login");
		adminDAO=new AdminDAO();
		
		//스타일
		Dimension d=new Dimension(360,45);
		t_id.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		
		
		setLayout(new FlowLayout());
		//조립
		add(t_id);
		add(t_pass);
		add(bt);
		
		
		setSize(400,220);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//재정의할 메서드가 달랑 1개일경우 굳이 클래스까지 거창하게 갈필요 없다
		//이때는 함수 기반의 코드 작성 방법으로 알려진 lambda표현식
		bt.addActionListener((e)->{
			loginCheck();
		});
	}
	//로그인이 성공되면 관리자 메인 프레임을 보이게 처리하자
	public void loginCheck() {
		//사용자가 입력한 아이디와 패스워드를 채워넣을 빈(empty)상태의 DTO생성
		String id=t_id.getText();//사용자 입력아이디
		String pass=new String(t_pass.getPassword());
		
		Admin admin=new Admin();//empty
		admin.setId(id);
		admin.setPass(pass);
		
		//login()메서드 호출후 반환되는DTO의 값이 null이 아니라면, 로그인 성공으로 판단
		adminDTO=adminDAO.login(admin);
		
		if(adminDTO==null) {//로그인 실패
			JOptionPane.showMessageDialog(this,"로그인정보가 올바르지 않습니다");
		}else {//로그인성공
			JOptionPane.showMessageDialog(this,"관리자 인증 성공");
			
			
			adminMain.login();
		
			
		}
		
	}
}
