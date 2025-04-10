async function removeFileToServer(uuid, originalFileName) {
    const response = await axios.delete(`/remove/${uuid}_${originalFileName}`);

    return response
}