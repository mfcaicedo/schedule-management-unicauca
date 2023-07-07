import { event } from "./event.model";
import { eventToSchedule } from "./eventToschedule.model";
export interface finalEventSchedule{
    event : event|null;
    eventToScheduleList : eventToSchedule[]; 
}