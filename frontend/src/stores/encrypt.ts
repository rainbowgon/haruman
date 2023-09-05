import { AES, enc } from "crypto-js";

// const secretKey: string | undefined = process.env.REACT_APP_REDUX_CRYPT_KEY;

// export function encryptData(data: any) {
//   if (!secretKey) {
//     throw new Error("REACT_APP_REDUX_CRYPT_KEY is not defined.");
//   }

//   return AES.encrypt(data, secretKey).toString();
// }

// export function decryptData(encrypted: any) {
//   if (!secretKey) {
//     throw new Error("REACT_APP_REDUX_CRYPT_KEY is not defined.");
//   }

//   const bytes = AES.decrypt(encrypted, secretKey);
//   return bytes.toString(enc.Utf8);
// }

////Secret Key 발급 이후 사용
