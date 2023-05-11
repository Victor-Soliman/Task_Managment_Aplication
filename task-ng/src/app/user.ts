// export class User {
//   // the ! sign means that it is assigned by default ,
//   // it should match the properties in the Entity
//   id!: number;
//   userName!: string;
//   password!: string;
// }
export interface User {
  // the ! sign means that it is assigned by default ,
  // it should match the properties in the Entity

  // username: string;
  email: string;
  password: string;
  username ?: string;
}
