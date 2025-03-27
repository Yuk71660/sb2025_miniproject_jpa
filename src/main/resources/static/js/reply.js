async function addReply(replyDTO) {
    let response = await axios.post('/replies/', replyDTO);
    return response;
}