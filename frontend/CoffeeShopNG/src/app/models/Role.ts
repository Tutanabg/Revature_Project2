
export class Role {
    roleID: number;
    roleName: string;

    constructor(roleName: string, roleID?: number){
        this.roleID = roleID;
        this.roleName = roleName;
    }

}