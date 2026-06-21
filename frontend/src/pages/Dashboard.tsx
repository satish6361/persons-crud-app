import { useEffect, useState } from "react";
import Header from "../components/Header";
import PersonFormDialog from "../components/PersonFormDialog";
import PersonTable from "../components/PersonTable";
import {
  createPerson,
  deletePerson,
  getPersons,
  updatePerson,
} from "../api/personApi";
import { toast } from "react-toastify";
import type { Person } from "../types/Person";
import DeleteConfirmationDialog from "../components/DeleteConfirmationDialog";
import Container from "@mui/material/Container";

export default function Dashboard() {
  const [openForm, setOpenForm] = useState(false);
  const [selectedPerson, setSelectedPerson] = useState<Person | null>(null);
  const [deleteOpen, setDeleteOpen] = useState(false);

  const [personToDelete, setPersonToDelete] = useState<Person | null>(null);
  const [persons, setPersons] = useState<Person[]>([]);

  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState(10);
  const [totalElements, setTotalElements] = useState(0);
  const loadPersons = async () => {
    try {
      setLoading(true);

      const response = await getPersons(page, pageSize);

      setPersons(response.data.data.content);
      setTotalElements(response.data.data.totalElements);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const handlePageChange = (newPage: number, newPageSize: number) => {
    setPage(newPage);
    setPageSize(newPageSize);
  };

  const handleDeletePerson = (person: Person) => {
    setPersonToDelete(person);
    setDeleteOpen(true);
  };

  const confirmDelete = async () => {
    if (!personToDelete?.id) return;

    try {
      await deletePerson(personToDelete.id);

      toast.success("Person deleted successfully");

      loadPersons();
    } catch {
      toast.error("Failed to delete person");
    } finally {
      setDeleteOpen(false);
      setPersonToDelete(null);
    }
  };

  const handleAddPerson = () => {
    setSelectedPerson(null);
    setOpenForm(true);
  };

  const handleEditPerson = (person: Person) => {
    setSelectedPerson(person);
    setOpenForm(true);
  };

  const handleCloseForm = () => {
    setOpenForm(false);
    setSelectedPerson(null);
  };

  const handleSubmitPerson = async (data: Person) => {
    try {
      if (selectedPerson?.id) {
        await updatePerson(selectedPerson.id, data);
        toast.success("Person updated successfully");
      } else {
        await createPerson(data);
        toast.success("Person created successfully");
      }

      loadPersons();
      handleCloseForm();

      return true;
    } catch (error) {
      toast.error("Failed to save person");
      throw error;
    }
  };
  useEffect(() => {
    loadPersons();
  }, [page, pageSize]);
  return (
    <Container maxWidth="xl">
      <Header onAddPerson={handleAddPerson} />
      <PersonTable
        persons={persons}
        loading={loading}
        page={page}
        pageSize={pageSize}
        totalElements={totalElements}
        onEdit={handleEditPerson}
        onDelete={handleDeletePerson}
        onPageChange={handlePageChange}
      />

      <PersonFormDialog
        key={selectedPerson?.id ?? "new"}
        open={openForm}
        onClose={handleCloseForm}
        onSubmit={handleSubmitPerson}
        person={selectedPerson}
      />

      <DeleteConfirmationDialog
        open={deleteOpen}
        person={personToDelete}
        onClose={() => {
          setDeleteOpen(false);
          setPersonToDelete(null);
        }}
        onConfirm={confirmDelete}
      />
    </Container>
  );
}
