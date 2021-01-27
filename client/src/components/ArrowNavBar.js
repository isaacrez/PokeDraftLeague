import React from '../../node_modules/react';

function ArrowNavBar(props) {

    function updatePage(shift) {
        props.setPage(page => {
            page += shift;

            if (page <= 0) {
                page += props.maxPages;
            } else if (props.maxPages < page) {
                page -= props.maxPages;
            }
            
            return page;
        })
    }

    return (
        <div>
            <button className="btn btn-secondary mx-1"
                onClick={() => (updatePage(-5))}>&#x00AB;</button>
            <button className="btn btn-secondary mx-1"
                onClick={() => (updatePage(-1))}>←</button>
            <button className="btn btn-secondary mx-1"
                onClick={() => (updatePage(1))}>→</button>
            <button className="btn btn-secondary mx-1"
                onClick={() => (updatePage(5))}>&#x00BB;</button>
        </div>
    );
}

export default ArrowNavBar;