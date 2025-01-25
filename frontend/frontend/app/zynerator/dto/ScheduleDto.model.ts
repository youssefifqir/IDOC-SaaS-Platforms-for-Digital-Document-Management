import {BaseDto} from './BaseDto.model';

export class ScheduleDto extends BaseDto {
    public subject: string;
    public startTime: Date;
    public endTime: Date;
}
