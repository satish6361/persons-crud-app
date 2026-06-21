import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Grid,
  MenuItem,
  TextField,
} from "@mui/material";
import { useEffect } from "react";
import { useForm } from "react-hook-form";

interface Person {
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

interface Props {
  open: boolean;
  onClose: () => void;
  onSubmit: (data: Person) => Promise<boolean>;
  person?: Person | null;
}

const defaultValues: Person = {
  name: "",
  email: "",
  gender: "",
  primaryMobile: "",
  secondaryMobile: "",
  aadhaar: "",
  pan: "",
  dateOfBirth: "",
  placeOfBirth: "",
  currentAddress: "",
  permanentAddress: "",
};

export default function PersonFormDialog({
  open,
  onClose,
  onSubmit,
  person,
}: Props) {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<Person>({
    defaultValues,
  });

  useEffect(() => {
    if (person) {
      reset(person);
    } else {
      reset(defaultValues);
    }
  }, [person, reset]);

  return (
    <Dialog open={open} onClose={onClose} fullWidth maxWidth="md">
      <DialogTitle
        sx={{
          fontWeight: 700,
          background: "#4F46E5",
          color: "white",
        }}
      >
        {person ? "Edit Person" : "Add Person"}
      </DialogTitle>

      <form
        onSubmit={handleSubmit(async (data) => {
          await onSubmit(data);
        })}
      >
        <DialogContent sx={{ mt: 2 }}>
          <Grid container spacing={2}>
            <Grid size={{ xs: 12, md: 6 }}>
              <TextField
                fullWidth
                label="Name"
                {...register("name", {
                  required: "Name is required",
                })}
                error={!!errors.name}
                helperText={errors.name?.message}
              />
            </Grid>

            <Grid size={{ xs: 12, md: 6 }}>
              <TextField
                fullWidth
                label="Email"
                {...register("email", {
                  required: "Email is required",
                })}
                error={!!errors.email}
                helperText={errors.email?.message}
              />
            </Grid>

            <Grid size={{ xs: 12, md: 6 }}>
              <TextField
                select
                fullWidth
                label="Gender"
                defaultValue=""
                {...register("gender")}
              >
                <MenuItem value="MALE">Male</MenuItem>

                <MenuItem value="FEMALE">Female</MenuItem>

                <MenuItem value="OTHER">Other</MenuItem>
              </TextField>
            </Grid>

            <Grid size={{ xs: 12, md: 6 }}>
              <TextField
                type="date"
                fullWidth
                slotProps={{
                  inputLabel: {
                    shrink: true,
                  },
                }}
                {...register("dateOfBirth")}
              />
            </Grid>

            <Grid size={{ xs: 12, md: 6 }}>
              <TextField
                fullWidth
                label="Primary Mobile"
                {...register("primaryMobile", {
                  required: "Primary mobile is required",
                  pattern: {
                    value: /^[6-9]\d{9}$/,
                    message: "Invalid mobile number",
                  },
                })}
                error={!!errors.primaryMobile}
                helperText={errors.primaryMobile?.message}
              />
            </Grid>

            <Grid size={{ xs: 12, md: 6 }}>
              <TextField
                fullWidth
                label="Secondary Mobile"
                {...register("secondaryMobile")}
              />
            </Grid>

            <Grid size={{ xs: 12, md: 6 }}>
              <TextField fullWidth label="Aadhaar" {...register("aadhaar")} />
            </Grid>

            <Grid size={{ xs: 12, md: 6 }}>
              <TextField fullWidth label="PAN" {...register("pan")} />
            </Grid>

            <Grid size={{ xs: 12 }}>
              <TextField
                fullWidth
                label="Place Of Birth"
                {...register("placeOfBirth")}
              />
            </Grid>

            <Grid size={{ xs: 12 }}>
              <TextField
                fullWidth
                multiline
                rows={3}
                label="Current Address"
                {...register("currentAddress", {
                  required: "Current address is required",
                })}
                error={!!errors.currentAddress}
                helperText={errors.currentAddress?.message}
              />
            </Grid>

            <Grid size={{ xs: 12 }}>
              <TextField
                fullWidth
                multiline
                rows={3}
                label="Permanent Address"
                {...register("permanentAddress")}
              />
            </Grid>
          </Grid>
        </DialogContent>

        <DialogActions sx={{ p: 3 }}>
          <Button variant="outlined" onClick={onClose}>
            Cancel
          </Button>

          <Button
            type="submit"
            variant="contained"
            sx={{
              background: "#4F46E5",
            }}
          >
            {person ? "Update" : "Save"}
          </Button>
        </DialogActions>
      </form>
    </Dialog>
  );
}
