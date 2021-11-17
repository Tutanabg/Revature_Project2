export class Picture {
    picID: number;
    picName: string;
    picType: Uint8Array;


  constructor(picID: number, picName: string, picType: Uint8Array) {
    this.picID = picID
    this.picName = picName
    this.picType = picType
  }

}