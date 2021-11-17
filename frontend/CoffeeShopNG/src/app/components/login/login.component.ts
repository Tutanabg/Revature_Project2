import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { User } from '../../models/User';
import { UserAddress } from '../../models/UserAddress';
import { CreateNewAccountService } from '../../services/create-new-account.service';
import { LoginService } from '../../services/login.service';
import { loginHttpService } from '../../services/login-http.service';
import { CustomvalidationService } from '../../services/customvalidation.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  registerForm: FormGroup;
  updateForm: FormGroup;
  submitted = false;
  updateSubmitted = false;

  constructor(
    private loginServ: LoginService,
    private fb: FormBuilder,
    private customValidator: CustomvalidationService,
    private createAccountHttp: CreateNewAccountService,
    private loginHttp: loginHttpService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.createAccountHttp.getAllUsernames().subscribe(
      (response) => {
        this.customValidator.UsernameList = response;
      }
    );
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      phoneNumber: ['', [Validators.required, Validators.pattern('[0-9]{3}[-]?[0-9]{3}[-]?[0-9]{4}$')]],
      email: ['', [Validators.required, Validators.email]],
      street: ['', Validators.required],
      city: ['', Validators.required],
      zipCode: ['', [Validators.required, Validators.pattern('[0-9]{5}')]],
      username: ['', [Validators.required], this.customValidator.userNameValidator.bind(this.customValidator)],
      password: ['', Validators.compose([Validators.required, this.customValidator.passwordValidator()])],
      confirmPassword: ['', [Validators.required]],
    },
      {
        validator: this.customValidator.MatchPassword('password', 'confirmPassword'),
      }
    );
    this.updateForm = this.fb.group({
      firstName2: ['', Validators.required],
      lastName2: ['', Validators.required],
      phoneNumber2: ['', [Validators.required, Validators.pattern('[0-9]{3}[-]?[0-9]{3}[-]?[0-9]{4}$')]],
      email2: ['', [Validators.required, Validators.email]],
      username2: ['', [Validators.required], this.customValidator.userNameMatchValidator.bind(this.customValidator)],
      password2: ['', Validators.compose([Validators.required, this.customValidator.passwordValidator()])],
      confirmPassword2: ['', [Validators.required]],
    },
      {
        validator: this.customValidator.MatchPassword('password2', 'confirmPassword2'),
      }
    );
  }



  username: string;
  password: string;
  
  newAccount: User = new User();
  updateAccount: User = new User();
  newAddress: UserAddress = new UserAddress();

  loginAccount: User;

  showErrorMessage: boolean = false;



  login() {
    if(this.username && this.password){
        this.loginAccount= new User(this.username, this.password);
        this.loginHttp.loginValidation(this.username, this.password).subscribe(
          (response) =>{
            if(response.status === 200){
              this.loginServ.login(response.body);
              this.goLandingPage();
            }else if (response.status === 204){
              alert("The username or password you entered does not match the records in our database. Please try again!")
            }else{
              alert("Something goes wrong! Please ask technology development for help!\n Email: abc@abc.com")
            }          
          }
        )
        this.clearText();
        this.showErrorMessage = false;
    }else{
      this.clearText();
      this.errorMessage();
    }
    
  }

  get registerFormControl() {
    return this.registerForm.controls;
  }

  get updateFormControl() {
    return this.updateForm.controls;
  }

  goLandingPage(){
    this.router.navigate(['/landingpage']);
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.filpSubmit();
      this.newAccount.email = this.registerForm.controls.email.value;
      this.newAccount.firstName = this.registerForm.controls.firstName.value;
      this.newAccount.lastName = this.registerForm.controls.lastName.value;
      this.newAccount.password = this.registerForm.controls.password.value;
      this.newAccount.phoneNumber = this.registerForm.controls.phoneNumber.value;
      this.newAccount.username = this.registerForm.controls.username.value;
      this.newAddress.street = this.registerForm.controls.street.value;
      this.newAddress.city = this.registerForm.controls.city.value;
      this.newAddress.zipCode = this.registerForm.controls.zipCode.value;
      this.createAccountHttp.addUser(this.newAccount).subscribe(
        (userResponse) => {
          if(userResponse.status === 201){
            this.newAddress.user = userResponse.body;
            this.createAccountHttp.addUserAddress(this.newAddress).subscribe(
              (addressResponse) => {
                if(addressResponse.status === 201){
                  this.loginServ.login(addressResponse.body);
                  this.goLandingPage();
                  this.filpSubmit();
                }else{
                  alert("Something goes wrong! Please ask technology development for help!\n Email: abc@abc.com")
                }    
              }
            );
          }else{
            alert("Something goes wrong! Please ask technology development for help!\n Email: abc@abc.com")
          }    
        }
      );
      this.registerForm.reset();
    }else{
      alert('Could not submit the Form!!!\n Check the values for all required fields.');
    }
  }

  onUpdateSubmit(){
    if (this.updateForm.valid) {
      this.filpUpdateSubmit();
      this.updateAccount.email = this.updateForm.controls.email2.value;
      this.updateAccount.firstName = this.updateForm.controls.firstName2.value;
      this.updateAccount.lastName = this.updateForm.controls.lastName2.value;
      this.updateAccount.password = this.updateForm.controls.password2.value;
      this.updateAccount.phoneNumber = this.updateForm.controls.phoneNumber2.value;
      this.updateAccount.username = this.updateForm.controls.username2.value;
      this.createAccountHttp.updateUser(this.updateAccount).subscribe(
        (userResponse) => {
          if(userResponse.status === 201){
            alert("Your Password has been changed!")
            this.filpUpdateSubmit();
          }else if (userResponse.status === 204){
            alert("The inputs you entered do not match the records in our database. Please try again!")
          }else{
            alert("Something goes wrong! Please ask technology development for help!\n Email: abc@abc.com")
          }}
        );
      this.updateForm.reset();
    }else{
      alert('Could not submit the Form!!!\n Check the values for all required fields.');
    }    
  }

  filpSubmit(){
    this.submitted = !this.submitted;
  }

  filpUpdateSubmit(){
    this.updateSubmitted = !this.updateSubmitted;
  }

  checkLogin(){
    return this.loginServ.currentUser;
  }

  clearText(){
    this.username = "";
    this.password = "";
  }

  errorMessage(){
    this.showErrorMessage = true;
  }
}
