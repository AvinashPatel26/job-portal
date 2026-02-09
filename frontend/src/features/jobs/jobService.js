import apiClient from "../../services/apiClient";

// MOCK for now
export const fetchJobs = async () => {
  return [
    { id: 1, title: "Java Developer", company: "TCS", location: "Bangalore" },
    { id: 2, title: "React Developer", company: "Infosys", location: "Pune" },
  ];
};

/*
 FUTURE (Node + MongoDB)
 export const fetchJobs = async () => {
   const response = await apiClient.get("/jobs");
   return response.data;
 };
*/
