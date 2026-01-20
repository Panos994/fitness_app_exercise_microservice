import React from 'react'
import {Grid2, Typography} from '@mui/material';
import { useNavigate } from 'react-router-dom';
const ActivityList = () => {
    const [activities, setActivities] = userState([]);
    const navigate = useNavigate();

    const fetchActivities = async () => {
        try {
            const response = await getActivities();
            setActivities(response.data);
        }catch(error){
            console.error(error);
        }
    }
    return (
        <Grid2 container spacing={2}>
            {activities.map((activity) =>(
                <Grid2 container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
                        <Card sx={{cursos: 'pointer'}} onClick {() => navigate(`/activities/${activity.id}`)}>
                    <CardContent>
                        <Typography variant='h6'>{activity.type}</Typography>
                        <Typography>Duration: {activity.duration}</Typography>
                        <Typography>Calories: {activity.caloriesBurned}</Typography>
                    </CardContent>

                        </Card>
                </Grid2>
            ))}
        </Grid2>
    );
};

export default ActivityList;