import { Typography } from '@mui/material';
import React, { act, use, useEffect, useState } from 'react'

const ActivityDetail = () => {
    const {id} = useParams();
    const [activity, setActivity] = useState(null);
    const [recommendation, setRecommendation] = useState(null);

    useEffect(() => {
        const fetchUserActivityDetail = async () => {
            try {
                const response = await getActivityDetail(id);
                setActivity(response.data);
                setRecommendation(response.data.recommendation);
            } catch (error) {
                console.error(error);
            }
        }
        fetchUserActivityDetail();
    }, [id]);
    if(!activity){
    return (
        <Typography>Loading...</Typography>
    )}
    return (
        <div>ActivityDetail</div>
    )
}

export default ActivityDetail