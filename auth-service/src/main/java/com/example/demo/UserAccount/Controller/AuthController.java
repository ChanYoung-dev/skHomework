package com.example.demo.UserAccount.Controller;

import com.example.demo.UserAccount.API.UserAccountAPI;
import com.example.demo.UserAccount.Exception.LoginException;
import com.example.demo.UserAccount.Exception.NoConnectionException;
import com.example.demo.UserAccount.Exception.NoSignUpException;
import com.example.demo.UserAccount.LoginAnnotation.Login;
import com.example.demo.UserAccount.Repository.UserAccountRepository;
import com.example.demo.UserAccount.Service.UserAccountService;
import com.example.demo.UserAccount.domain.UserAccount;
import com.example.demo.UserAccount.dto.*;
import com.example.demo.UserAccount.vo.RequestSignUp;
import com.example.demo.UserAccount.vo.RequestUserAccount;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//import login.domain.UserAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	@Value("${msa.domain}")
	String serverDomain;

	@Value("${msa.auth}")
	String serverAuth;

	@Value("${msa.member}")
	String serverMember;

	private final UserAccountService userAccountService;
	private final UserAccountRepository userAccountRepository;
	private final UserAccountAPI userAccountAPI;



	@GetMapping("/healthcheck")
	@ResponseBody
	public String test() {
		return "auth-service working..";
	}

	@GetMapping("/login")
	public String loginForm(@Login UserAccount userAccount, Model model, @RequestParam(required = false) String redirectURL) {
		if(userAccount != null){
			return "redirect:/";
		}
		model.addAttribute("serverDomain", serverDomain);
		model.addAttribute("redirectURL",redirectURL);
		return "loginForm";
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody RequestUserAccount requestUserAccount, HttpServletResponse response,
								HttpServletRequest request) {

		// RequestUserAccount -> Dto
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserAccountDto dto = mapper.map(requestUserAccount, UserAccountDto.class);

		// 아이디,패스워드 검증
		UserAccount loginMember = userAccountService.login(dto);

		//세션에 로그인 정보 저장
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", loginMember);
		return ResponseEntity.status(HttpStatus.OK).build();
	}



	@GetMapping("/sign-up")
	public String signUpForm(Model model){
		model.addAttribute("serverAuth",serverAuth);
		return "auth/signUpForm";
	}

	@PostMapping("/sign-up")
	public ResponseEntity signUp(@RequestBody RequestSignUp dto){
		userAccountService.register(dto.getId(), dto.getPassword(), dto.getName(), dto.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}


	@PostMapping("/id-validation")
	public ResponseEntity idValidation(@RequestBody IdValidationRequestDto dto) {
		if (userAccountService.isIdDuplicated(dto.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}

	@PostMapping("/logout")
	@ResponseBody
	public ResponseEntity logout(@Login UserAccount loginMember, HttpServletRequest request) {
		System.out.println(" reload....");
		userAccountService.controlLoginYN(loginMember);
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return ResponseEntity
				.status(HttpStatus.OK)
				.build();
	}

	@GetMapping("/logout")
	public String logout2(@Login UserAccount loginMember, HttpServletRequest request) {
		//webclient.post
		loginMember.setLoginYN("N");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "/";
	}



	@ExceptionHandler
	@ResponseBody
	public ResponseEntity loginExceptionHandler(LoginException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler
	public String noSignUpExceptionHandler(NoSignUpException e){
		userAccountAPI.deleteUserInfo("emrhssla");
		return "/";
	}





}
