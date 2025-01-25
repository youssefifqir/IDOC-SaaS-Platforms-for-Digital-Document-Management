import {BaseDto} from "../../../zynerator/dto/BaseDto.model";


export class MinIOInfos extends BaseDto{
    private bucketName :string;
    private originalFileName :string;
    private fileSize :number;
    private bytes :number;
    private etag :string;
    private resultStatus:number;


    constructor(bucketName: string, originalFileName: string, fileSize: number, bytes: number, etag: string, resultStatus: number) {
        super();
        this.bucketName = bucketName;
        this.originalFileName = originalFileName;
        this.fileSize = fileSize;
        this.bytes = bytes;
        this.etag = etag;
        this.resultStatus = resultStatus;
    }
}











