
export interface User {
  // the ! sign means that it is assigned by default ,
  // it should match the properties in the Entity

  email: string;
  password: string;
  username ?: string;
}
