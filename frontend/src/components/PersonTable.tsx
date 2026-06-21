import { DataGrid } from "@mui/x-data-grid";
import { IconButton } from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";

export default function PersonTable({
  persons,
  loading,
  page,
  pageSize,
  onEdit,
  onDelete,
  onPageChange,
}: any) {
  const columns = [
    {
      field: "name",
      headerName: "Name",
      flex: 1,
    },
    {
      field: "email",
      headerName: "Email",
      flex: 1.5,
    },
    {
      field: "primaryMobile",
      headerName: "Mobile",
      flex: 1,
    },
    {
      field: "gender",
      headerName: "Gender",
      flex: 1,
    },
    {
      field: "actions",
      headerName: "Actions",
      width: 150,
      renderCell: (params: any) => (
        <>
          <IconButton color="primary" onClick={() => onEdit(params.row)}>
            <EditIcon />
          </IconButton>

          <IconButton color="error" onClick={() => onDelete(params.row)}>
            <DeleteIcon />
          </IconButton>
        </>
      ),
    },
  ];

  return (
    <DataGrid
      rows={persons}
      columns={columns}
      loading={loading}
      disableRowSelectionOnClick
      paginationMode="server"
      // rowCount={totalElements}
      paginationModel={{
        page,
        pageSize,
      }}
      onPaginationModelChange={(model) => {
        onPageChange(model.page, model.pageSize);
      }}
      pageSizeOptions={[5, 10, 20]}
      autoHeight
    />
  );
}
