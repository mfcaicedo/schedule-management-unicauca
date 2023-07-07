export interface Auth{
  token:string;
  bearer:string;
  username:string;
  authorities:{authority:string}[]
}
