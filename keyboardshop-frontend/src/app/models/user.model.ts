
export interface User {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  streetName: string;
  houseNumber: string;
  postalCode: string;
  city: string;
  phoneNumber: string;
  password?: string;
}