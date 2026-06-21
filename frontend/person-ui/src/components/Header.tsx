import { AppBar, Toolbar, Typography, Button, Box } from "@mui/material";
import PersonAddIcon from "@mui/icons-material/PersonAdd";
import GroupsIcon from "@mui/icons-material/Groups";

interface HeaderProps {
  onAddPerson: () => void;
}

export default function Header({ onAddPerson }: HeaderProps) {
  return (
    <AppBar
      position="static"
      elevation={0}
      sx={{
        borderRadius: 4,
        mb: 3,
        background: "#4F46E5",
      }}
    >
      <Toolbar
        sx={{
          py: 2,
          display: "flex",
          justifyContent: "space-between",
        }}
      >
        <Box
          sx={{
            display: "flex",
            alignItems: "center",
            gap: 2,
          }}
        >
          <GroupsIcon
            sx={{
              fontSize: 40,
            }}
          />

          <Box>
            <Typography sx={{ variant: "h5", fontWeight: "bold" }}>
              Person Management
            </Typography>

            <Typography
              variant="body2"
              sx={{
                opacity: 0.85,
              }}
            >
              Manage persons efficiently
            </Typography>
          </Box>
        </Box>

        <Button
          variant="contained"
          startIcon={<PersonAddIcon />}
          onClick={onAddPerson}
          sx={{
            bgcolor: "white",
            color: "#4F46E5",
            fontWeight: 600,
            px: 3,
            py: 1.2,
            borderRadius: 3,
            textTransform: "none",
            "&:hover": {
              bgcolor: "#F5F5F5",
            },
          }}
        >
          Add Person
        </Button>
      </Toolbar>
    </AppBar>
  );
}
