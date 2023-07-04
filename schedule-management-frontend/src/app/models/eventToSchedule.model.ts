import { Environment } from "./environment.model";

export interface eventToSchedule{
    startingDate: string;
    startingTime: string;
    endingTime : string;
    recurrence : string;
    day : string;
    weeks : string|null;
    environmentId : number;
    endingDate:string;
    }

    export interface eventToScheduleAUX{
        startingDate: string;
        startingTime: string;
        endingTime : string;
        recurrence : string;
        day : string;
        weeks : string|null;
        environment : Environment;
        }