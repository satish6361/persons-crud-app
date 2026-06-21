import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
});

export const getPersons = (page = 0, size = 10) =>
  api.get(`/persons?page=${page}&size=${size}`);

export const createPerson = (data: any) => api.post("/persons", data);

export const updatePerson = (id: number, data: any) =>
  api.put(`/persons/${id}`, data);

export const deletePerson = (id: number) => api.delete(`/persons/${id}`);
