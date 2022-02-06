import * as React from 'react';
import LinearProgress from '@mui/material/LinearProgress';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import {useEffect} from "react";

function LinearProgressWithLabel(props) {


    return (
        <Box sx={{display: 'flex', alignItems: 'center'}}>
            <Box sx={{width: '80%', mr: 1, ml: 1}}>
                <LinearProgress variant="determinate" {...props} />
            </Box>
            <Box sx={{minWidth: 35}}>
                <Typography variant="body2" color="text.secondary">{`${Math.round(
                    props.value,
                )}%`}</Typography>
            </Box>
        </Box>
    );
}


export default function ProgressBar(props) {
    const [progress, setProgress] = React.useState(0);


    useEffect(() => {
        setProgress(parseInt(props.percentage))
    }, [props.percentage])


    return (
        <Box sx={{}}>
            <LinearProgressWithLabel value={progress}/>
        </Box>
    );
}