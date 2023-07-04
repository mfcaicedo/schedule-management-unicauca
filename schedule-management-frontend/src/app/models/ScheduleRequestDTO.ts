export interface ScheduleRequestDTO{
    
    day:string,
    endingDate:string,
    endingTime:string,
    isReserv:boolean,
    startingDate:string,
    startingTime:string,
    courseId:number,
    environmentId:number,
    eventId:number
}