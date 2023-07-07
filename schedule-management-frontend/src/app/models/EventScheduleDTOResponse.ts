import { ScheduleRequestDTO } from "./ScheduleRequestDTO";
import { event } from "./event.model";

export interface EventScheduleDTOResponse{
    event:event,
    eventScheduleList:ScheduleRequestDTO[];

}