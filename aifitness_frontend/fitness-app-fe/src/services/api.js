const API_URL = 'http://localhost:9192/api/'

const api = axios.create({
  baseURL: API_URL
})

api.interceptors.request.use((config) => {
  const userId = localStorage.getItem('userId');
  if(userId){
    config.headers['X-User-ID'] = userId;
}

export const getActivities = () => api.get('/activities'); 
export const addActivity = () => api.post('/activity', activity); 
export const getActivityDetail = () => api.get('/recommendations/activity/${id}');
