import { Component, OnInit } from '@angular/core';
import { FormControl, FormControlName, FormGroup, Validators } from '@angular/forms';


import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';
import {UserDto} from 'src/app/zynerator/security/shared/model/User.model';
import {RoleDto} from 'src/app/zynerator/security/shared/model/Role.model';
import {RoleUserDto} from 'src/app/zynerator/security/shared/model/RoleUser.model';


@Component({
  selector: 'app-register-utilisateur',
  templateUrl: './register-utilisateur.component.html',
  styleUrls: ['./register-utilisateur.component.css']
})
export class RegisterUtilisateurComponent implements OnInit {
  registerForm = new FormGroup({
    phone : new FormControl('', Validators.required),
    firstName : new FormControl('', Validators.required),
    lastName : new FormControl('', Validators.required),
    email : new FormControl('', Validators.required),
    userName : new FormControl('', Validators.required),
    password : new FormControl('', Validators.required)
  });
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  submit(){
    const formValues = this.registerForm.value
    const {phone,firstName, lastName, email ,userName, password } = formValues;
    const role = new RoleDto();
    role.authority = 'ROLE_Admin' ;
	const roleUser = new RoleUserDto();
    roleUser.role = role;
    this.user.firstName = firstName;
    this.user.lastName = lastName;
    this.user.phone = phone;
    this.user.username = userName;
    this.user.password = password;
    this.user.email = email;
	this.user.roleUsers = [roleUser] ;
    this.authService.registerAdmin();
  }

  get user(): UserDto {
    return this.authService.user;
  }

  set user(value: UserDto) {
    this.authService.user = value;
  }

}
