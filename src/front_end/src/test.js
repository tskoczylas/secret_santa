import DateAdapter from '@mui/lab/AdapterMoment';
import {LocalizationProvider} from "@mui/lab";
import {DataGrid} from "@mui/x-data-grid";
import * as React from "react";

export default function Test(){


const rows = [
    {
        id: 1,
        username: '@MUI',
        age: 20,
    },
];

return(
    <div>
        <DataGrid
            columns={[
                {
                    field: 'username',
                    headerName: 'Username',
                    description:
                        'The identification used by the person with access to the online service.',
                },
                { field: 'age', headerName: 'Age' },
            ]}
            rows={rows}
        />
    </div>

)}