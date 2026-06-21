export interface Person {
  id?: number;
  name: string;
  email: string;
  gender: string;

  primaryMobile: string;
  secondaryMobile: string;

  aadhaar: string;
  pan: string;

  dateOfBirth: string;
  placeOfBirth: string;

  currentAddress: string;
  permanentAddress: string;
}
