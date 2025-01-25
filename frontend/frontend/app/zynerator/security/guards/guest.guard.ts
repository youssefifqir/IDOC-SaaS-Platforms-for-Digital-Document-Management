import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';

@Injectable({
    providedIn: 'root'
})
export class GuestGuard implements CanActivate {
    constructor(private authService: AuthService, private router: Router) {
    }

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        if (this.authService.authenticated) {
            console.log('hehllowo ');
            this.router.navigate(['/app']);
            return false;
        }
        return true;
    }
}
