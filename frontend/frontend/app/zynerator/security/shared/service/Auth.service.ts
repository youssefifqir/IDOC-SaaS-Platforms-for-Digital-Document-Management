import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

import {environment} from 'src/environments/environment';

import {BehaviorSubject} from 'rxjs';


import {TokenService} from './Token.service';
import {UserDto} from '../model/User.model';
import {RoleDto} from '../model/Role.model';
import {RoleUserDto} from '../model/RoleUser.model';
import {MessageService} from 'primeng/api';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    readonly API = environment.loginUrl;
    public _user = new UserDto();
    private _authenticatedUser = new UserDto();
    private _authenticated = (JSON.parse(localStorage.getItem('autenticated')) as boolean) || false;
    public _loggedIn = new BehaviorSubject<boolean>(false);
    public loggedIn$ = this._loggedIn.asObservable();
    public error: string = null;


    constructor(private http: HttpClient, private tokenService: TokenService, private router: Router, private messageService: MessageService) {
    }

    public login(username: string, password: string) {
        this.http.post<any>(this.API + 'login', {username, password}, {observe: 'response'}).subscribe(
            resp => {
                console.log(resp);
                this.error = null;
                const jwt = 'Bearer '+resp.body.accessToken;
                jwt != null ? this.tokenService.saveToken(jwt) : false;
                this.loadInfos();
                console.log('you are logged in successfully');
                this.router.navigate(['/' + environment.rootAppUrl + '/admin']);
            }, (error: HttpErrorResponse) => {
                this.error = error.error.message;
                if (error.status === 401) {
                    let errorMessage = '';
                    if (this.error && error.message) {
                        errorMessage = error.error.message;
                    } else {
                        errorMessage = 'Unauthorized: Invalid credentials';
                    }
                    if (errorMessage.toLowerCase().includes('user is disabled')) {
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Error ' + error.status,
                            detail: 'Unauthorized: User is disabled'
                        });
                    } else {
                        this.messageService.add({severity: 'error', summary: 'Error ' + error.status, detail: errorMessage});
                    }
                }else if (error.status === 405) {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Error ' + error.status,
                        detail: 'Method Not Allowed: Please check your request method'
                    });
                } else {
                    this.messageService.add({severity: 'error', summary: 'Error ' + error.status, detail: 'An unexpected error occurred'});
                }
            }
        );
    }

    public loadInfos() {
        const tokenDecoded = this.tokenService.decode();
        const username = tokenDecoded.sub;
        const roles = tokenDecoded.roles;
        const email = tokenDecoded.email;
        const firstName = tokenDecoded.firstName;
        const lastName = tokenDecoded.lastName;
        const phone = tokenDecoded.phone;
        const passwordChanged = tokenDecoded.passwordChanged;
        this._authenticatedUser.passwordChanged = passwordChanged;
        this._authenticatedUser.username = username;
        this._authenticatedUser.phone = phone;
        this._authenticatedUser.firstName = firstName;
        this._authenticatedUser.lastName = lastName;
        this._authenticatedUser.email = email;
        roles.forEach(role => {
            const roleUser = new RoleUserDto();
            roleUser.role.authority = role;
            this._authenticatedUser.roleUsers.push(roleUser);
        });
        localStorage.setItem('autenticated', JSON.stringify(true));
        this.authenticated = true;
        this._loggedIn.next(true);

    }


    public hasRole(role: RoleDto): boolean {
        const index = this._authenticatedUser.roleUsers.findIndex(r => r.role.authority === role.authority);
        return index > -1 ? true : false;
    }

    public registerAdmin() {
        this.http.post<any>(this.API + 'api/user/', this.user, {observe: 'response'}).subscribe(
            resp => {
                this.router.navigate(['admin/login']);
            }, (error: HttpErrorResponse) => {
                console.log(error.error);
            }
        );
    }

    public logout() {
        this.tokenService.removeToken();
        localStorage.setItem('autenticated', JSON.stringify(false));
        this.authenticated = false;
        this._loggedIn.next(false);
        this._authenticatedUser = new UserDto();
        this.router.navigate(['']);
    }

    get user(): UserDto {
        return this._user;
    }

    set user(value: UserDto) {
        this._user = value;
    }

    get authenticated(): boolean {

        return this._authenticated;
    }

    set authenticated(value: boolean) {
        this._authenticated = value;
    }

    get authenticatedUser(): UserDto {
        return this._authenticatedUser;
    }

    set authenticatedUser(value: UserDto) {
        this._authenticatedUser = value;
    }


}
