import * as React from 'react';
import PropTypes from 'prop-types';
import LinearProgress from '@mui/material/LinearProgress';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { TokenContex } from '../pages/TokenMain';

function LinearProgressWithLabel(props) {


  return (
    <Box sx={{ display: 'flex', alignItems: 'center' }}>
      <Box sx={{ width: '100%', mr: 1 }}>
        <LinearProgress variant="determinate" {...props} />
      </Box>
      <Box sx={{ minWidth: 35 }}>
        <Typography variant="body2" color="text.secondary">{`${Math.round(
          props.value,
        )}%`}</Typography>
      </Box>
    </Box>
  );
}



LinearProgressWithLabel.propTypes = {
  
  value: PropTypes.number.isRequired,
};

export default function ProgressBar() {
  const [progress, setProgress] = React.useState(10);

  const userData=React.useContext(TokenContex)



  React.useEffect(() => {
    const timer = setInterval(() => {
      setProgress((prevProgress) => (prevProgress >= 10 ? userData.admin.percentageCompleteUsers : prevProgress + 10));
    }, 800);
    return () => {
      clearInterval(timer);
    };
  },[]);

  return (
      <LinearProgressWithLabel value={progress} />
  );
}