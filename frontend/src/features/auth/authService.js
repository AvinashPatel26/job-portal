import apiClient from "../../services/apiClient";

// MOCK
export const loginUser = async (credentials) => {
  return {
    token: "mock-jwt-token",
    user: { email: credentials.email, role: "USER" },
  };
};

/*
 FUTURE
 export const loginUser = async (credentials) => {
   const response = await apiClient.post("/auth/login", credentials);
   return response.data;
 };
*/
